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
package com.agentflow.planning;

/**
 * Interface for agent planning strategies.
 * Responsible for decomposing goals into steps and orchestrating execution.
 */
public interface Planner {
    
    /**
     * Plan and execute the given steps.
     *
     * @Steps the steps to execute
     * @param agent the agent instance
     * @return execution result
     */
    ToolExecutionResult planAndExecute(List<String> steps, AgentRuntime agent);
    
    /**
     * Decompose a natural language goal into executable steps.
     */
    List<String> decomposeGoal(String goal);
    
    /**
     * Determine the next step to execute given current state.
     */
    String determineNextStep(List<String> remainingSteps, 
                             agentRuntime.CurrentState state);
}

/**
 * Simple planner that executes steps sequentially.
 */
public class SequentialPlanner implements Planner {
    
    @Override
    public ToolExecutionResult planAndExecute(List<String> steps, AgentRuntime agent) {
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < steps.size() && i < agent.getConfig().getMaxSteps(); i++) {
            String step = steps.get(i);
            output.append(String.format("Step %d: %s\n", i + 1, step));
            output.append("  [Executing...]\n");
            
            // In a full implementation, we'd check for tool calls here
            // and execute registered tools
        }
        
        return new ToolExecutionResult(
            "execution complete",
            output.toString(),
            true
        );
    }
    
    @Override
    public List<String> decomposeGoal(String goal) {
        // Simple decomposition - split by period
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
    
    @Override
    public String determineNextStep(List<String> remainingSteps, 
                                    CurrentState state) {
        if (remainingSteps.isEmpty()) {
            return "complete";
        }
        return remainingSteps.get(0);
    }
}

/**
 * Current state of the agent runtime.
 */
public class CurrentState {
    private final boolean isRunning;
    private final String currentStep;
    private final int stepCount;
    private final Map<String, Object> memory;
    
    public CurrentState(boolean isRunning, String currentStep, int stepCount, Map<String, Object> memory) {
        this.isRunning = isRunning;
        this.currentStep = currentStep;
        this.stepCount = stepCount;
        this.memory = memory;
    }
    
    public boolean isRunning() { return isRunning; }
    public String getCurrentStep() { return currentStep; }
    public int getStepCount() { return stepCount; }
    public Map<String, Object> getMemory() { return memory; }
}