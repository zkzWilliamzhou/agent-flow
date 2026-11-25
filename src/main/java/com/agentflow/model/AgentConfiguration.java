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
package com.agentflow.model;

import com.agentflow.memory.MemorySystem;
import com.agentflow.planning.Planner;
import com.agentflow.tool.Tool;
import java.util.List;
import java.util.Map;

/**
 * Immutable configuration for an AI agent.
 * Holds all the settings needed to construct and run an agent.
 */
public class AgentConfiguration {
    
    private String agentName;
    private AgentType agentType;
    private List<Tool> tools;
    private MemorySystem memory;
    private Planner planner;
    private String systemPrompt;
    private String modelProvider;
    private String modelName;
    private boolean autoRun;
    private int maxSteps;
    
    public AgentConfiguration() {}
    
    // Getters and setters
    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }
    
    public AgentType getAgentType() { return agentType; }
    public void setAgentType(AgentType agentType) { this.agentType = agentType; }
    
    public List<Tool> getTools() { return tools; }
    public void setTools(List<Tool> tools) { this.tools = tools; }
    
    public MemorySystem getMemory() { return memory; }
    public void setMemory(MemorySystem memory) { this.memory = memory; }
    
    public Planner getPlanner() { return planner; }
    public void setPlanner(Planner planner) { this.planner = planner; }
    
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }
    
    public String getModelProvider() { return modelProvider; }
    public void setModelProvider(String modelProvider) { this.modelProvider = modelProvider; }
    
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    
    public boolean isAutoRun() { return autoRun; }
    public void setAutoRun(boolean autoRun) { this.autoRun = autoRun; }
    
    public int getMaxSteps() { return maxSteps; }
    public void setMaxSteps(int maxSteps) { this.maxSteps = maxSteps; }
}