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
package com.agentflow.tool;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for managing available tools that agents can use.
 * Tools can be registered and looked up by name.
 */
public class ToolRegistry {
    
    private final java.util.Map<String, Tool> tools = new ConcurrentHashMap<>();
    
    /**
     * Register a tool.
     */
    public void register(Tool tool) {
        if (tool != null) {
            tools.put(tool.getName(), tool);
        }
    }
    
    /**
     * Get a tool by name.
     */
    public Tool get(String name) {
        return tools.get(name);
    }
    
    /**
     * Execute a tool by name with the given parameters.
     */
    public ToolExecutionResult execute(String name, Map<String, Object> parameters) {
        Tool tool = tools.get(name);
        if (tool == null) {
            return new ToolExecutionResult(
                "error",
                "Tool not found: " + name,
                false
            );
        }
        try {
            return tool.execute(parameters);
        } catch (Exception e) {
            return new ToolExecutionResult(
                "error",
                "Tool execution failed: " + e.getMessage(),
                false
            );
        }
    }
    
    /**
     * Get all registered tool names.
     */
    public java.util.List<String> getToolNames() {
        return new java.util.ArrayList<>(tools.keySet());
    }
    
    /**
     * Check if a tool is registered.
     */
    public boolean contains(String name) {
        return tools.containsKey(name);
    }
}