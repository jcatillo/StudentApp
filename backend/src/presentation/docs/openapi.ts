export const openApiSpec = {
  openapi: "3.0.3",
  info: {
    title: "StudentApp API",
    version: "1.0.0",
    description: "Clean Architecture backend API for Student resources.",
  },
  servers: [
    {
      url: "http://localhost:3000",
      description: "Local development",
    },
  ],
  tags: [
    {
      name: "Auth",
      description: "Authentication endpoints",
    },
    {
      name: "Students",
      description: "Student management endpoints",
    },
  ],
  paths: {
    "/api/v1/auth/login": {
      post: {
        tags: ["Auth"],
        summary: "Authenticate student credentials",
        requestBody: {
          required: true,
          content: {
            "application/json": {
              schema: { $ref: "#/components/schemas/LoginRequest" },
            },
          },
        },
        responses: {
          "200": {
            description: "Authentication successful",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/LoginResponse" },
              },
            },
          },
          "400": {
            description: "Validation error",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/ErrorResponse" },
              },
            },
          },
          "401": {
            description: "Invalid credentials",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/ErrorResponse" },
              },
            },
          },
        },
      },
    },
    "/api/v1/students/{id}": {
      get: {
        tags: ["Students"],
        summary: "Get a student profile by id",
        parameters: [
          {
            name: "id",
            in: "path",
            required: true,
            schema: { type: "string" },
          },
        ],
        responses: {
          "200": {
            description: "Student profile found",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/StudentProfileResponse" },
              },
            },
          },
          "404": {
            description: "Student not found",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/ErrorResponse" },
              },
            },
          },
        },
      },
      put: {
        tags: ["Students"],
        summary: "Update a student profile",
        security: [{ BearerAuth: [] }],
        parameters: [
          {
            name: "id",
            in: "path",
            required: true,
            schema: { type: "string" },
          },
        ],
        requestBody: {
          required: true,
          content: {
            "application/json": {
              schema: { $ref: "#/components/schemas/UpdateStudentProfileRequest" },
            },
          },
        },
        responses: {
          "200": {
            description: "Student profile updated",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/StudentProfileResponse" },
              },
            },
          },
          "400": {
            description: "Validation error",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/ErrorResponse" },
              },
            },
          },
          "404": {
            description: "Student not found",
            content: {
              "application/json": {
                schema: { $ref: "#/components/schemas/ErrorResponse" },
              },
            },
          },
        },
      },
    },
  },
  components: {
    securitySchemes: {
      BearerAuth: {
        type: "http",
        scheme: "bearer",
        bearerFormat: "JWT",
      },
    },
    schemas: {
      StudentProfile: {
        type: "object",
        properties: {
          id: { type: "string" },
          fullName: { type: "string" },
          emailAddress: { type: "string", format: "email" },
          phoneNumber: { type: "string" },
          accountLabel: { type: "string" },
          programSummary: { type: "string" },
          twoFactorStatus: {
            type: "string",
            enum: ["Disabled", "PendingVerification", "Enabled"],
          },
          emergencyContactName: { type: "string" },
          emergencyContactRelationship: { type: "string" },
          emergencyContactPhoneNumber: { type: "string" },
          emailNotifications: { type: "boolean" },
          smsNotifications: { type: "boolean" },
          systemAlerts: { type: "boolean" },
          createdAt: { type: "string", format: "date-time" },
          updatedAt: { type: "string", format: "date-time" },
        },
        required: [
          "id",
          "fullName",
          "emailAddress",
          "phoneNumber",
          "accountLabel",
          "programSummary",
          "twoFactorStatus",
          "emergencyContactName",
          "emergencyContactRelationship",
          "emergencyContactPhoneNumber",
          "emailNotifications",
          "smsNotifications",
          "systemAlerts",
          "createdAt",
          "updatedAt",
        ],
      },
      StudentProfileResponse: {
        type: "object",
        properties: {
          success: { type: "boolean" },
          data: { $ref: "#/components/schemas/StudentProfile" },
        },
        required: ["success", "data"],
      },
      LoginData: {
        type: "object",
        properties: {
          accessToken: { type: "string" },
          tokenType: { type: "string", enum: ["Bearer"] },
          expiresIn: { type: "string" },
          refreshToken: { type: "string" },
        },
        required: ["accessToken", "tokenType", "expiresIn"],
      },
      LoginResponse: {
        type: "object",
        properties: {
          success: { type: "boolean" },
          data: { $ref: "#/components/schemas/LoginData" },
        },
        required: ["success", "data"],
      },
      LoginRequest: {
        type: "object",
        properties: {
          studentId: { type: "string", minLength: 1 },
          password: { type: "string", minLength: 1 },
          keepLoggedIn: { type: "boolean", default: false },
        },
        required: ["studentId", "password", "keepLoggedIn"],
      },
      UpdateStudentProfileRequest: {
        type: "object",
        properties: {
          fullName: { type: "string", minLength: 1 },
          emailAddress: { type: "string", format: "email" },
          phoneNumber: { type: "string", minLength: 1 },
          accountLabel: { type: "string", minLength: 1 },
          programSummary: { type: "string", minLength: 1 },
          twoFactorStatus: {
            type: "string",
            enum: ["Disabled", "PendingVerification", "Enabled"],
          },
          emergencyContactName: { type: "string", minLength: 1 },
          emergencyContactRelationship: { type: "string", minLength: 1 },
          emergencyContactPhoneNumber: { type: "string", minLength: 1 },
          emailNotifications: { type: "boolean" },
          smsNotifications: { type: "boolean" },
          systemAlerts: { type: "boolean" },
        },
      },
      ErrorResponse: {
        type: "object",
        properties: {
          success: { type: "boolean" },
          error: {
            type: "object",
            properties: {
              code: { type: "string" },
              message: { type: "string" },
            },
            required: ["code", "message"],
          },
        },
        required: ["success", "error"],
      },
    },
  },
} as const;
