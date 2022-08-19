---
page_type: sample
languages:
- java
products:
- azure-functions
- azure
description: "This repository contains sample for Azure Functions in Java"
urlFragment: "azure-functions-java"
---

# Azure Functions samples in Java

## Contents

Outline the file contents of the repository. It helps users navigate the codebase, build configuration and any related assets.

| File/folder       | Description                                |
|-------------------|--------------------------------------------|
| `src`             | Sample source code.                        |
| `.gitignore`      | Define what to ignore at commit time.      |
| `build.gradle`    | The gradle configuration to this sample.   |
| `pom.xml`         | The maven configuration to this sample.   |
| `CHANGELOG.md`    | List of changes to the sample.             |
| `CONTRIBUTING.md` | Guidelines for contributing to the sample. |
| `README.md`       | This README file.                          |
| `LICENSE.txt`         | The license for the sample.                |

## Prerequisites

- Gradle 4.10+
- Latest [Function Core Tools](https://aka.ms/azfunc-install)
- Azure CLI. This plugin use Azure CLI for authentication, please make sure you have Azure CLI installed and logged in.

## Setup

- ```cmd
    az login
    az account set -s <your subscription id>
    ```
- Update the Application settings in Azure portal with the required parameters as below
  - AzureWebJobsStorage: Connection string to your storage account
  - Documentation on how to [manage connection strings](https://docs.microsoft.com/en-gb/azure/storage/common/storage-account-keys-manage?tabs=azure-portal) and [access keys](https://docs.microsoft.com/en-gb/azure/storage/common/storage-configure-connection-string#create-a-connection-string-for-an-azure-storage-account)
- Update `host.json` with the right extension bundle version. `V3 - [1.*, 2.0.0) and V4 - [2.*, 3.0.0)`


## Running the sample

```cmd
./mvnw clean package azure-functions:run
```

```cmd
./gradlew clean azureFunctionsRun
```

## Deploy the sample on Azure


```cmd
./mvnw clean package azure-functions:deploy
```

```cmd
./gradlew clean azureFunctionsDeploy
```

> NOTE: please replace '/' with '\\' when you are running on windows.

