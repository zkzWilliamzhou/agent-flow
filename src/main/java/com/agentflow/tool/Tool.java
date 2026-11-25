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

/**
 * Interface for AI agent tools.
 * Each tool represents a callable function that the agent can invoke.
 */
public interface Tool {
    
    /**
     * Get the tool name/identifier.
     */
    String getName();
    
    /**
     * Get the tool description.
     */
    String getDescription();
    
    /**
     * Execute the tool with the given parameters.
     *
     * @param parameters the parameters for the tool execution
     * @return the execution result
     */
    ToolExecutionResult execute(Map<String, Object> parameters);
}

/**
 * Result of a tool execution.
 */
public class ToolExecutionResult {
    
    private final String status;
    private final String output;
    private final boolean success;
    private final Map<String, Object> metadata;
    
    public ToolExecutionResult(String status, String output, boolean success) {
        this(status, output, success, null);
    }
    
    public ToolExecutionResult(String status, String output, boolean success, 
                               Map<String, Object> metadata) {
        this.status = status;
        this.output = output;
        this.success = success;
        this.metadata = metadata;
    }
    
    public String getStatus() { return status; }
    public String getOutput() { return output; }
    public boolean isSuccess() { return success; }
    public Map<String, Object> getMetadata() { return metadata; }
    
    @Override
    public String toString() {
        return String.format("ToolExecutionResult{status='%s', success=%b, output length=%d}", 
            status, success, output != null ? output.length() : 0);
    }
}