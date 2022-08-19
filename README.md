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

## Prerequisites

- Gradle 4.10+
- Latest [Function Core Tools](https://aka.ms/azfunc-install) [in this repo build/tools/func]
- Azure CLI. This plugin use Azure CLI for authentication, please make sure you have Azure CLI installed and logged in.

## Setup

- Update the Application settings in Azure portal with the required parameters as below
  - AzureWebJobsStorage: Connection string to your storage account
  - Documentation on how to [manage connection strings](https://docs.microsoft.com/en-gb/azure/storage/common/storage-account-keys-manage?tabs=azure-portal) and [access keys](https://docs.microsoft.com/en-gb/azure/storage/common/storage-configure-connection-string#create-a-connection-string-for-an-azure-storage-account)

- ```bash
    az login
    az account set -s <your subscription id>
    # deploy:
    sh setup/deploy.sh
    ```

## Running the sample

```cmd
cd code/
```

```cmd
./mvnw clean package azure-functions:run
```

#### Deploy the sample on Azure

```cmd
./mvnw clean package azure-functions:deploy
```

> NOTE: please replace '/' with '\\' when you are running on windows.

