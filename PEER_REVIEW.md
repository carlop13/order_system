# Peer Review Log - Sprint 2

This document records the issues identified and solutions implemented during the partial peer review process for Sprint 2, focusing on configuration management.

---

### **Issue #1: Security Risk - Hardcoded Database Credentials**

- **Date Identified:** 18/10/2025
- **File:** `application-prod.properties` (Initial Draft)

#### **Problem Description**
During a self-review of the configuration for the production environment, a critical security vulnerability was identified. The initial draft of `application-prod.properties` contained the database username and password written directly in the file (hardcoded). Storing sensitive credentials in source code is a major security risk, as anyone with access to the repository (even old versions) could view them.

#### **Solution Implemented**
To mitigate this risk, the configuration was refactored to use environment variables for sensitive data.

**The implemented solution is as follows:**
1.  The `spring.datasource.username` and `spring.datasource.password` properties in `application-prod.properties` were changed to use placeholders: `${DB_USERNAME}` and `${DB_PASSWORD}`.
2.  This delegates the responsibility of providing credentials to the server environment where the application is deployed.
3.  The `README.md` file was updated with clear instructions on how to set these environment variables before running the application with the `prod` profile.

This change ensures that no sensitive information is ever committed to the version control system, adhering to security best practices.