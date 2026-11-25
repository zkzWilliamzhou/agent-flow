# AgentFlow: End-to-End AI Agent Toolchain for Java

[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](/actions)

## 🌟 What is AgentFlow?

AgentFlow is the **first complete toolchain for building AI agents from Java source code**. It bridges the gap between traditional Java development and modern AI agent architectures by providing:

- **Automated Agent Generation**: Transform Java services into AI agents automatically
- **Tool Integration**: Seamlessly expose Java methods as agent tools
- **Memory & Planning**: Built-in persistent memory and task planning
- **Multi-Agent Orchestration**: Coordinate multiple agents for complex workflows
- **Production-Ready**: Designed for enterprise deployment from day one

## 🚀 Key Features

### 🏗️ Agent Builder
- **Annotation-Driven**: `@Agent`, `@Tool`, `@Memory` annotations
- **Auto-Discovery**: Scans codebase for agent candidates
- **Configuration as Code**: YAML/JSON for complex agent definitions
- **Template Library**: Pre-built agent archetypes (RAG, ReAct, Plan-and-Execute)

### 🔧 Tool Integration
- **Method Exposure**: Any public method → AI callable tool
- **Schema Generation**: Automatic OpenAPI/JSON Schema from Java types
- **Validation**: Input/output validation with detailed error messages
- **Observability**: Built-in tracing and metrics for every tool call

### 🧠 Memory & Planning
- **Episodic Memory**: Conversation history with semantic search
- **Semantic Memory**: Vector-based knowledge storage
- **Procedural Memory**: Learned skills and workflows
- **Hierarchical Planning**: Decompose goals into executable steps

### 🤝 Multi-Agent Orchestration
- **Agent Teams**: Define agent roles and communication protocols
- **Workflow Engine**: Visual workflow designer + code execution
- **State Management**: Distributed state with consistency guarantees
- **Human-in-the-Loop**: Checkpoint approval for critical decisions

## 📦 Quick Start

### Maven Setup
```xml
<dependency>
    <groupId>com.agentflow</groupId>
    <artifactId>agentflow-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Define an Agent
```java
@Agent(name = "CodeReviewAgent", description = "Reviews Java code for quality issues")
public class CodeReviewAgent {
    
    @Tool(description = "Analyze a Java file for code smells")
    public AnalysisResult analyzeCode(@ToolParam String filePath) {
        // Your analysis logic here
        return new AnalysisResult();
    }
    
    @Tool(description = "Apply automated fix")
    public FixResult applyFix(@ToolParam String filePath, @ToolParam String fixType) {
        // Your fix logic here
        return new FixResult();
    }
}
```

### Run the Agent
```java
AgentFlow flow = AgentFlow.builder()
    .withAgent(new CodeReviewAgent())
    .withMemory(new VectorMemory())
    .withPlanner(new HierarchicalPlanner())
    .build();

AgentSession session = flow.createSession();
String result = session.execute("Review the UserController.java file");
```

## 🎯 Why AgentFlow?

| Feature | LangChain4j | Spring AI | AgentFlow |
|---------|------------|-----------|-----------|
| Java-First | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| Source-to-Agent | ❌ | ❌ | ✅ |
| Multi-Agent | ⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐⭐ |
| Production Ops | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| Visual Builder | ❌ | ❌ | ✅ |

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────────────┐
│                        AgentFlow Platform                            │
├─────────────────────────────────────────────────────────────────────┤
│  ┌─────────────────┐  ┌─────────────────┐  ┌───────────────────┐  │
│  │ Agent Builder   │  │ Agent Runtime   │  │ Agent Orchestrator│  │
│  │ ┌─────────────┐ │  │ ┌─────────────┐ │  │ ┌───────────────┐ │  │
│  │ │ Annotation  │ │  │ │ Session     │ │  │ │ Workflow      │ │  │
│  │ │ Processor   │ │  │ │ Manager     │ │  │ │ Engine        │ │  │
│  │ │ Template    │ │  │ │ Tool        │ │  │ │ State         │ │  │
│  │ │ Engine      │ │  │ │ Executor    │ │  │ │ Manager       │ │  │
│  │ │ Schema Gen  │ │  │ │ Memory      │ │  │ │ Human Loop    │ │  │
│  │ └─────────────┘ │  │ └─────────────┘ │  │ └───────────────┘ │  │
│  └─────────────────┘  └─────────────────┘  └───────────────────┘  │
│  ┌─────────────────┐  ┌─────────────────┐  ┌───────────────────┐  │
│  │ Model Gateway   │  │ Memory System   │  │ Observability     │  │
│  │ ┌─────────────┐ │  │ ┌─────────────┐ │  │ ┌───────────────┐ │  │
│  │ │ Multi-LLM   │ │  │ │ Episodic    │ │  │ │ Distributed   │ │  │
│  │ │ Routing     │ │  │ │ Semantic    │ │  │ │ Tracing       │ │  │
│  │ │ Fallback    │ │  │ │ Procedural  │ │  │ │ Metrics       │ │  │
│  │ │ Cost Ctrl   │ │  │ │ Vector DB   │ │  │ │ Debugging     │ │  │
│  │ └─────────────┘ │  │ └─────────────┘ │  │ └───────────────┘ │  │
│  └─────────────────┘  └─────────────────┘  └───────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

## 📈 Use Cases

1. **Code Review Agent**: Automate PR reviews with custom rules
2. **Documentation Agent**: Generate and maintain API docs
3. **Test Generation Agent**: Create unit tests from specifications
4. **Refactoring Agent**: Safely apply large-scale refactorings
5. **Security Audit Agent**: Continuous security scanning
6. **DevOps Agent**: Automate deployment and incident response

## 🤝 Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## 📄 License

Apache License 2.0 - See [LICENSE](LICENSE)

---

**Build AI agents from Java code today!** ⭐ Star this repo!