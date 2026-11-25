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
package com.agentflow.memory;

/**
 * Interface for agent memory systems.
 * Provides episodic, semantic, and procedural memory capabilities.
 */
public interface MemorySystem {
    
    /**
     * Store a memory item with the given key.
     *
     * @param key the memory key
     * @param value the memory value
     */
    void store(String key, Object value);
    
    /**
     * Recall a memory item by key.
     *
     * @param key the memory key
     * @return the stored value, or null if not found
     */
    Object recall(String key);
    
    /**
     * Initialize the memory system.
     */
    void initialize();
    
    /**
     * Store episodic memory (conversation history, session state).
     *
     * @param key the memory key
     * @param value the value to store
     */
    default void storeEpisodic(String key, Object value) {
        store(key, value);
    }
    
    /**
     * Recall episodic memory.
     *
     * @param key the memory key
     * @return the stored value, or null if not found
     */
    default Object recallEpisodic(String key) {
        return recall(key);
    }
    
    /**
     * Store semantic memory (facts, knowledge, patterns).
     */
    default void storeSemantic(String key, Object value) {
        store(key, value);
    }
    
    /**
     * Recall semantic memory.
     */
    default Object recallSemantic(String key) {
        return recall(key);
    }
    
    /**
     * Store procedural memory (skills, workflows, learned patterns).
     */
    default void storeProcedural(String key, Object value) {
        store(key, value);
    }
    
    /**
     * Recall procedural memory.
     */
    default Object recallProcedural(String key) {
        return recall(key);
    }
}

/**
 * Simple in-memory implementation of MemorySystem.
 */
public class InMemoryMemorySystem implements MemorySystem {
    
    private final java.util.Map<String, Object> storage = new java.util.HashMap<>();
    
    @Override
    public void store(String key, Object value) {
        storage.put(key, value);
    }
    
    @Override
    public Object recall(String key) {
        return storage.get(key);
    }
    
    @Override
    public void initialize() {
        // No-op for in-memory system
    }
}