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
import com.agentflow.model.ToolSchema;
import com.agentflow.memory.MemorySystem;
import com.agentflow.planning.Planner;
import com.agentflow.model.AgentType;

import java.util.List;
import java.util.Map;

/**
 * Builder pattern for creating AI agent configurations.
 * Supports annotation-driven agent discovery and configuration.
 */
public class AgentBuilder {

    private String agentName;
    private AgentType agentType;
    private List<ToolSchema> tools;
    private MemorySystem memory;
    private Planner planner;
    private String systemPrompt;
    private String modelProvider;
    private String modelName;
    private boolean autoRun;
    private int maxSteps;

    private AgentBuilder() {}

    public static AgentBuilder builder() {
        return new AgentBuilder();
    }

    public AgentBuilder agentName(String name) {
        this.agentName = name;
        return this;
    }

    public AgentBuilder agentType(AgentType type) {
        this.agentType = type;
        return this;
    }

    public AgentBuilder tools(List<ToolSchema> tools) {
        this.tools = tools;
        return this;
    }

    public AgentBuilder memory(MemorySystem memory) {
        this.memory = memory;
        return this;
    }

    public AgentBuilder planner(Planner planner) {
        this.planner = planner;
        return this;
    }

    public AgentBuilder systemPrompt(String prompt) {
        this.systemPrompt = prompt;
        return this;
    }

    public AgentBuilder modelProvider(String provider) {
        this.modelProvider = provider;
        return this;
    }

    public AgentBuilder modelName(String name) {
        this.modelName = name;
        return this;
    }

    public AgentBuilder autoRun(boolean autoRun) {
        this.autoRun = autoRun;
        return this;
    }

    public AgentBuilder maxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
        return this;
    }

    /**
     * Build the agent configuration.
     *
     * @return immutable agent configuration
     */
    public AgentConfiguration build() {
        AgentConfiguration config = new AgentConfiguration();
        config.setAgentName(agentName != null ? agentName : "DefaultAgent");
        config.setAgentType(agentType != null ? agentType : AgentType.REACT);
        config.setTools(tools != null ? tools : List.of());
        config.setMemory(memory);
        config.setPlanner(planner);
        config.setSystemPrompt(systemPrompt);
        config.setModelProvider(modelProvider != null ? modelProvider : "openai");
        config.setModelName(modelName != null ? modelName : "gpt-4o");
        config.setAutoRun(autoRun);
        config.setMaxSteps(maxSteps > 0 ? maxSteps : 10);
        return config;
    }
}