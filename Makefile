SHELL := /bin/sh

MVNW := ./mvnw
MVN := $(if $(wildcard $(MVNW)),$(MVNW),mvn)

.PHONY: help deps build run test clean package verify fmt

help: ## Show available targets
	@printf "Available targets:\n"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-12s %s\n", $$1, $$2}'

deps: ## Download dependencies
	$(MVN) dependency:resolve

build: ## Compile and package without tests
	$(MVN) clean package -DskipTests

run: ## Run the Spring Boot app
	$(MVN) spring-boot:run

test: ## Run tests
	$(MVN) test

package: ## Package the application jar
	$(MVN) package

verify: ## Run full verification lifecycle
	$(MVN) verify

fmt: ## Placeholder formatting target
	@echo "No formatter configured for this project"

clean: ## Remove build artifacts
	$(MVN) clean
