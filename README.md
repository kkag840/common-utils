# Common Utils

A centralized common utilities module containing reusable helpers, shared constants, and cross-cutting components for microservice-based applications.

## 📋 Project Overview

**Common Utils** is a Spring Boot library that provides shared functionality for microservice-based applications. It serves as a foundation by offering standardized data models, enums, utilities, and response structures that can be reused across multiple projects.

### Key Information
- **Version**: 0.0.1
- **Group ID**: com.common
- **Artifact ID**: common-utils
- **Java Version**: 17
- **Spring Boot Version**: 4.0.1

## 🎯 Features

### Enums
- **ErrorCode**: Standardized error codes for the application
- **RegistrationType**: User registration type definitions

### Models & DTOs
- **BaseDao**: Base data access object for persistence
- **CommonResponse**: Standardized API response wrapper with message, status, and record count
- **ListResponseDTO**: Generic list response container with pagination support
- **PageRequest**: Pagination request parameters
- **UserBaseDetail**: Base user model with validation annotations
- **CalendarType**: Calendar type definitions

### Utilities
- **DateUtils**: Date manipulation and formatting utilities
- **PasswordUtil**: Password hashing and validation (MD5)
- **Constants**: Application-wide constants including:
  - HTTP headers (x-userId, x-authorization, x-access-token, etc.)
  - Date/Time formats (dd/MM/yyyy, yyyy-MM-dd, HH:mm)
  - Token expiration times
  - Time zone configurations (Default: Asia/Kolkata)
  - Cache keys and status codes

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/common/
│   │   ├── CommonUtilsApplication.java          # Spring Boot entry point
│   │   ├── enums/
│   │   │   ├── ErrorCode.java                   # Error code definitions
│   │   │   └── RegistrationType.java            # Registration types
│   │   ├── model/
│   │   │   ├── BaseDao.java                     # Base persistence entity
│   │   │   ├── CalendarType.java                # Calendar type model
│   │   │   ├── ListResponseDTO.java             # Paginated list response
│   │   │   ├── PageRequest.java                 # Pagination parameters
│   │   │   ├── response/
│   │   │   │   └── CommonResponse.java          # Standard API response
│   │   │   └── user/
│   │   │       └── UserBaseDetail.java          # User base model with validation
│   │   └── utils/
│   │       ├── Constants.java                   # Application constants
│   │       ├── DateUtils.java                   # Date utilities
│   │       └── PasswordUtil.java                # Password utilities
│   └── resources/
│       ├── application.properties                # Spring Boot properties
│       ├── messages.properties                   # Default locale messages
│       ├── messages_en_IN.properties             # English (India) messages
│       └── messages_hi_IN.properties             # Hindi (India) messages
└── test/
    └── java/com/common/
        └── CommonUtilsApplicationTests.java      # Integration tests
```

## 🔧 Technologies & Dependencies

### Core Framework
- **Spring Boot** (v4.0.1): Application framework
- **Maven**: Build tool and dependency management
- **Java 17**: Programming language

### Key Dependencies
- **Lombok**: Boilerplate code reduction (getters, setters, builders)
- **Jackson**: JSON processing and annotations
- **Jakarta Persistence API**: JPA support
- **Jakarta XML Bind**: XML binding support
- **Spring Boot Validation**: Input validation (Jakarta Validation API)
- **Spring Boot Test**: Testing framework with JUnit 5
- **Exception Handler** (custom): Exception handling utility from GitHub

### Repository
- **JitPack**: Used for hosting custom GitHub dependencies

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build the Project

```bash
# Clean and build
mvn clean install

# Build without running tests
mvn clean install -DskipTests
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CommonUtilsApplicationTests
```

### Package the Project

```bash
# Create JAR artifact
mvn clean package
```

The compiled JAR will be generated at: `target/common-utils-0.0.1.jar`

## 📦 Usage as a Dependency

To use this library in your microservice projects, add the following to your project's `pom.xml`:

```xml
<dependency>
    <groupId>com.github.kkag840</groupId>
    <artifactId>common-utils</artifactId>
    <version>0.0.1</version>
</dependency>
```

Make sure your project has the JitPack repository configured:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

## 💡 Key Components Usage

### CommonResponse
Standard API response format for all endpoints:

```java
@RestController
public class MyController {
    @GetMapping("/api/data")
    public CommonResponse getData() {
        return CommonResponse.builder()
            .status("Success")
            .message("Data retrieved successfully")
            .updateRecords(10L)
            .build();
    }
}
```

### Constants
Access application-wide constants:

```java
import com.common.utils.Constants;

public class MyService {
    public void process() {
        String userId = request.getHeader(Constants.HEADER_USERID);
        String timezone = Constants.DEFAULT_TIME_ZONE; // "Asia/Kolkata"
        String dateFormat = Constants.DATE_FORMAT;      // "dd/MM/yyyy"
    }
}
```

## 📋 Error Codes

All error codes are defined in the `ErrorCode` enum with corresponding messages:

| Enum Name | Code | Message |
|-----------|------|---------|
| INVALID_DATA | COMMON_UTILS_001 | Invalid data provided. Please check your request. |
| RESOURCE_NOT_FOUND | COMMON_UTILS_002 | Requested resource not found. |
| OPERATION_FAILED | COMMON_UTILS_003 | Operation failed. Please try again later. |
| TIMEOUT_OCCURRED | COMMON_UTILS_004 | Request timeout occurred. Please try again. |
| CONFLICT_ERROR | COMMON_UTILS_005 | Conflict error. The resource already exists or has been modified. |
| INTERNAL_SERVER_ERROR | COMMON_UTILS_006 | An internal server error occurred. Please try again later. |
| SERVICE_UNAVAILABLE | COMMON_UTILS_007 | Service is temporarily unavailable. Please try again later. |
| CONNECTION_FAILED | COMMON_UTILS_008 | Connection to the service failed. Please try again. |
| LANGUAGE_NOT_SUPPORT | COMMON_UTILS_009 | Language is not supported. |
| DATABASE_OPERATION_FAILED | COMMON_UTILS_010 | Database operation failed. Please try again later. |

## 📋 HTTP Headers

The application uses custom HTTP headers for cross-cutting concerns:

| Header | Constant | Purpose |
|--------|----------|---------|
| x-userId | HEADER_USERID | User identification |
| x-authorization | HEADER_AUTHORIZATION | Authorization token |
| x-access-token | HEADER_ACCESS_TOKEN | Access token |
| x-refresh-authorization-token | HEADER_AUTHORIZATION_REFRESH | Refresh token |
| x-client-ip | CLIENT_IP | Client IP tracking |
| Device-Token | HEADER_DEVICE_TOKEN | Device identification |
| user-agent | HEADER_USER_AGENT | User agent information |

## ⏱️ Token & Expiration Times

- **Verification Token**: 10 minutes (600,000 ms)
- **Authorization Token**: 8 hours
- **Forgot Password Token**: 4 hours
- **Token Update Requested Cache Key**: TOKEN_UPDATE_REQUESTED_

## 🌍 Timezone & Date Formats

- **Default Timezone**: Asia/Kolkata (GMT+5:30)
- **Date Format**: dd/MM/yyyy
- **Year-Month-Date Format**: yyyy-MM-dd
- **Time Format**: HH:mm
- **Milliseconds in a Day**: 86,400,000 ms

## 🔐 Security

- **Password Algorithm**: MD5
- Uses custom exception handler for centralized error handling
- Validation annotations for input validation

## 📝 Message Localization

Messages are available in multiple locales:

### Supported Locales
- **Default (English)**: `messages.properties`
- **English (India)**: `messages_en_IN.properties`
- **Hindi (India)**: `messages_hi_IN.properties`

### Message Categories
- Error Code Messages (ErrorCode enum names)
- Validation Messages
- Success Messages
- Authentication & Authorization Messages
- User Messages

## 🧪 Testing

The project includes unit and integration tests:

- **Test Framework**: Spring Boot Test (JUnit 5)
- **Test Location**: `src/test/java/com/common/`
- **Example Test Class**: `CommonUtilsApplicationTests.java`

Run tests with:
```bash
mvn test
```

## 📝 Configuration

The Spring Boot application properties can be found in:
- `src/main/resources/application.properties`

## 🛠️ Build Configuration

- **Packaging**: JAR (Library)
- **Repackaging**: Disabled (configured as a library, not an executable application)
- **Plugin**: Spring Boot Maven Plugin with skip=true

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/)
- [Lombok Documentation](https://projectlombok.org/)
- [JitPack](https://jitpack.io/)

## 📧 Support

For issues, questions, or contributions, please refer to the project documentation or create an issue in the repository.

---

**Last Updated**: December 27, 2025  
**Project Version**: 0.0.1

