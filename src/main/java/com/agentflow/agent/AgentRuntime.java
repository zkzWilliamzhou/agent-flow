/*
 * Copyright 2024 AgentFlow Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agentflow.agent;

import com.agentflow.model.AgentConfiguration;
import com.agentflow.model.ToolExecutionResult;
import com.agentflow.memory.MemorySystem;
import com.agentflow.planning.Planner;
import com.agentflow.tool.Tool;
import com.agentflow.tool.ToolRegistry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Runtime execution environment for AI agents.
 * Manages agent sessions, tool execution, memory, and workflow orchestration.
 */
public class AgentRuntime {
    
    private final AgentConfiguration config;
    private final MemorySystem memory;
    private final Planner planner;
    private final ToolRegistry toolRegistry;
    private final Map<String, Object> sessionState = new ConcurrentHashMap<>();
    private volatile boolean running = false;
    
    public AgentRuntime(AgentConfiguration config) {
        this.config = config;
        this.memory = config.getMemory();
        this.planner = config.getPlanner();
        this.toolRegistry = new ToolRegistry();
    }
    
    /**
     * Start an agent session and execute the given goal.
     *
     * @param goal the goal/task for the agent to accomplish
     * @return the execution result
     */
    public ToolExecutionResult execute(String goal) {
        running = true;
        try {
            // Initialize session memory
            if (memory != null) {
                memory.initialize();
            }
            
            // Parse the goal into steps/plans
            List<String> steps = parseGoal(goal);
            
            // Execute using the planner if available
            if (planner != null) {
                return planner.planAndExecute(steps, this);
            }
            
            // Simple execution without planner
            return executeSimple(steps);
        } finally {
            running = false;
        }
    }
    
    /**
     * Execute steps simply without planner.
     */
    private ToolExecutionResult executeSimple(List<String> steps) {
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < steps.size() && i < config.getMaxSteps(); i++) {
            String step = steps.get(i);
            output.append(String.format("Step %d: %s\n", i + 1, step));
            
            // Check if we have tools for this step
            // In a full implementation, we'd match steps to tools
            output.append("  [Executing...]\n");
        }
        
        return new ToolExecutionResult(
            "execution complete",
            output.toString(),
            true
        );
    }
    
    /**
     * Parse a natural language goal into executable steps.
     */
    private List<String> parseGoal(String goal) {
        // Simple parsing - in production, use LLM to decompose goal
        java.util.List<String> steps = new java.util.ArrayList<>();
        String[] parts = goal.split("\\.");
        for (String part : parts) {
            part = part.trim().replaceAll("\\s+", " ");
            if (!part.isEmpty()) {
                steps.add(part);
            }
        }
        if (steps.isEmpty()) {
            steps.add(goal);
        }
        return steps;
    }
    
    /**
     * Register a tool for the agent to use.
     */
    public void registerTool(Tool tool) {
        toolRegistry.register(tool);
    }
    
    /**
     * Execute a tool by name.
     */
    public ToolExecutionResult executeTool(String toolName, Map<String, Object> parameters) {
        return toolRegistry.execute(toolName, parameters);
    }
    
    /**
     * Store a memory item.
     */
    public void remember(String key, Object value) {
        if (memory != null) {
            memory.store(key, value);
        } else {
            sessionState.put(key, value);
        }
    }
    
    /**
     * Retrieve a memory item.
     */
    @SuppressWarnings("unchecked")
    public <T> T recall(String key) {
        if (memory != null) {
            return (T) memory.recall(key);
        }
        return (T) sessionState.get(key);
    }
    
    /**
     * Get the agent configuration.
     */
    public AgentConfiguration getConfig() {
        return config;
    }
    
    /**
     * Check if the agent is currently running.
     */
    public boolean isRunning() {
        return running;
    }
}