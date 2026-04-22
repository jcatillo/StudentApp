# Dev Roadmap — StudentApp

## Phase 0 — Project Scaffold
> Run once. Do not revisit.

- [ ] `npm init -y`
- [ ] `npm install express zod dotenv drizzle-orm pg drizzle-kit jsonwebtoken bcryptjs`
- [ ] `npm install -D typescript tsx @types/express @types/node @types/jsonwebtoken @types/bcryptjs vitest supertest`
- [ ] `tsconfig.json` — strict mode, path alias `@/*` → `src/*`
- [ ] `.env.example` with `DATABASE_URL`, `PORT`, `JWT_SECRET`
- [ ] Create directories: `src/core/entities` `src/core/errors` `src/core/types` `src/application/repositories` `src/application/dtos` `src/application/use-cases` `src/infrastructure/db/schema` `src/infrastructure/db/repositories` `src/infrastructure/db/migrations` `src/infrastructure/config` `src/presentation/routes` `src/presentation/controllers` `src/presentation/middleware` `src/presentation/lib`
- [ ] `src/container.ts` — DI wiring root only
- [ ] `src/main.ts` — import app and call `app.listen(env.PORT)`
- [ ] `src/app.ts` — `express()`, `express.json()`, CORS, router mounts, error middleware

---

## Phase 1 — Shared Infrastructure
> Complete before starting any Feature Block.

- [ ] `src/infrastructure/config/env.ts` — Zod-parse env and export typed `env`
- [ ] `src/infrastructure/db/client.ts` — pg Pool + Drizzle singleton
- [ ] `src/core/errors/domain.error.ts` — `DomainError`, `NotFoundError`, `ConflictError`, `UnauthorizedError`, `ForbiddenError`
- [ ] `src/application/dtos/pagination.dto.ts` — `PaginationDto` (`page`, `limit` with defaults/limits)
- [ ] `src/presentation/lib/response.helper.ts` — `ok`, `created`, `noData`, `badRequest`
- [ ] `src/presentation/middleware/validate.middleware.ts` — body safeParse and `VALIDATION_ERROR`
- [ ] `src/presentation/middleware/error.middleware.ts` — map error codes to statuses, unknown → `INTERNAL_ERROR`

---

## Feature: Program
> Routes: `GET /programs` · `GET /programs/:id`
> Depends on: none

### Step 1 — Core
- [ ] `src/core/entities/program.entity.ts` — plain `Program` type

### Step 2 — Application
- [ ] `src/application/repositories/program.repository.ts` — `findAll(pagination)` and `findById(id)`
- [ ] `src/application/dtos/program.dto.ts` — `CreateProgramDTO`, `UpdateProgramDTO`, `ProgramResponseDTO`
- [ ] `src/application/use-cases/program/get-programs.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/program/get-program.use-case.ts` — by id, throw `NotFoundError` when absent

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/program.schema.ts` — `programs` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/program.pg.repository.ts` — implement pagination/count mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/program.controller.ts` — `getPrograms`, `getProgram`
- [ ] `src/presentation/routes/program.routes.ts` — `GET /programs`, `GET /programs/:id`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add Program repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/programs', programRouter)`

---

## Feature: StudentProfile
> Routes: `GET /students/:id` · `PUT /students/:id`
> Depends on: none

### Step 1 — Core
- [ ] `src/core/entities/student-profile.entity.ts` — plain `StudentProfile` type

### Step 2 — Application
- [ ] `src/application/repositories/student-profile.repository.ts` — `findById(id)`, `update(id, patch)`
- [ ] `src/application/dtos/student-profile.dto.ts` — `CreateStudentProfileDTO`, `UpdateStudentProfileDTO`, `StudentProfileResponseDTO`
- [ ] `src/application/use-cases/student-profile/get-student-profile.use-case.ts` — by id + `NotFoundError`
- [ ] `src/application/use-cases/student-profile/update-student-profile.use-case.ts` — find first, merge partial, save

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/student-profile.schema.ts` — `student_profiles` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/student-profile.pg.repository.ts` — implement find/update mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/student-profile.controller.ts` — `getStudentProfile`, `updateStudentProfile`
- [ ] `src/presentation/routes/student-profile.routes.ts` — `GET /students/:id`, `PUT /students/:id`, `validate(UpdateStudentProfileDTO)` on PUT, protect with `authenticate`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add StudentProfile repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/students', studentProfileRouter)`

---

## Feature: LibraryBook
> Routes: `GET /library-books` · `PUT /library-books/:id`
> Depends on: none

### Step 1 — Core
- [ ] `src/core/entities/library-book.entity.ts` — plain `LibraryBook` type

### Step 2 — Application
- [ ] `src/application/repositories/library-book.repository.ts` — `findAll(pagination)`, `findById(id)`, `update(id, patch)`
- [ ] `src/application/dtos/library-book.dto.ts` — `CreateLibraryBookDTO`, `UpdateLibraryBookDTO`, `LibraryBookResponseDTO`
- [ ] `src/application/use-cases/library-book/get-library-books.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/library-book/update-library-book.use-case.ts` — find first, patch, save

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/library-book.schema.ts` — `library_books` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/library-book.pg.repository.ts` — implement list/update + count
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/library-book.controller.ts` — `getLibraryBooks`, `updateLibraryBook`
- [ ] `src/presentation/routes/library-book.routes.ts` — `GET /library-books`, `PUT /library-books/:id`, `validate(UpdateLibraryBookDTO)` on PUT

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add LibraryBook repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/library-books', libraryBookRouter)`

---

## Feature: Course
> Routes: `GET /courses`
> Depends on: Program

### Step 1 — Core
- [ ] `src/core/entities/course.entity.ts` — plain `Course` type (`programId` optional FK) // clarify before implementing

### Step 2 — Application
- [ ] `src/application/repositories/course.repository.ts` — `findAll(pagination)`
- [ ] `src/application/dtos/course.dto.ts` — `CreateCourseDTO`, `UpdateCourseDTO`, `CourseResponseDTO`
- [ ] `src/application/use-cases/course/get-courses.use-case.ts` — paginated list, return `{ data, total }`

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/course.schema.ts` — `courses` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/course.pg.repository.ts` — implement list + count mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/course.controller.ts` — `getCourses`
- [ ] `src/presentation/routes/course.routes.ts` — `GET /courses`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add Course repo, use case, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/courses', courseRouter)`

---

## Feature: ScheduleEntry
> Routes: `GET /schedule`
> Depends on: StudentProfile, Course

### Step 1 — Core
- [ ] `src/core/entities/schedule-entry.entity.ts` — plain `ScheduleEntry` type

### Step 2 — Application
- [ ] `src/application/repositories/schedule-entry.repository.ts` — `findAll(pagination)`
- [ ] `src/application/dtos/schedule-entry.dto.ts` — `CreateScheduleEntryDTO`, `UpdateScheduleEntryDTO`, `ScheduleEntryResponseDTO`
- [ ] `src/application/use-cases/schedule-entry/get-schedule-entries.use-case.ts` — paginated list, return `{ data, total }`

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/schedule-entry.schema.ts` — `schedule_entries` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/schedule-entry.pg.repository.ts` — implement list + count mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/schedule-entry.controller.ts` — `getScheduleEntries`
- [ ] `src/presentation/routes/schedule-entry.routes.ts` — `GET /schedule`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add ScheduleEntry repo, use case, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/schedule', scheduleEntryRouter)`

---

## Feature: GradeRecord
> Routes: `GET /grades`
> Depends on: StudentProfile, Course

### Step 1 — Core
- [ ] `src/core/entities/grade-record.entity.ts` — plain `GradeRecord` type (`codeCredits`, `gradePoint` formats) // clarify before implementing

### Step 2 — Application
- [ ] `src/application/repositories/grade-record.repository.ts` — `findAll(pagination)`
- [ ] `src/application/dtos/grade-record.dto.ts` — `CreateGradeRecordDTO`, `UpdateGradeRecordDTO`, `GradeRecordResponseDTO`
- [ ] `src/application/use-cases/grade-record/get-grade-records.use-case.ts` — paginated list, return `{ data, total }`

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/grade-record.schema.ts` — `grade_records` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/grade-record.pg.repository.ts` — implement list + count mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/grade-record.controller.ts` — `getGradeRecords`
- [ ] `src/presentation/routes/grade-record.routes.ts` — `GET /grades`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add GradeRecord repo, use case, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/grades', gradeRecordRouter)`

---

## Feature: Transaction
> Routes: `GET /transactions`
> Depends on: StudentProfile

### Step 1 — Core
- [ ] `src/core/entities/transaction.entity.ts` — plain `Transaction` type (`amount` representation) // clarify before implementing

### Step 2 — Application
- [ ] `src/application/repositories/transaction.repository.ts` — `findAll(pagination)`
- [ ] `src/application/dtos/transaction.dto.ts` — `CreateTransactionDTO`, `UpdateTransactionDTO`, `TransactionResponseDTO`
- [ ] `src/application/use-cases/transaction/get-transactions.use-case.ts` — paginated list, return `{ data, total }`

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/transaction.schema.ts` — `transactions` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/transaction.pg.repository.ts` — implement list + count mapping
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/transaction.controller.ts` — `getTransactions`
- [ ] `src/presentation/routes/transaction.routes.ts` — `GET /transactions`

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add Transaction repo, use case, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/transactions', transactionRouter)`

---

## Feature: DocumentRequest
> Routes: `GET /document-requests` · `GET /document-requests/:id` · `POST /document-requests`
> Depends on: StudentProfile

### Step 1 — Core
- [ ] `src/core/entities/document-request.entity.ts` — plain `DocumentRequest` type

### Step 2 — Application
- [ ] `src/application/repositories/document-request.repository.ts` — `findAll(pagination)`, `findById(id)`, `create(input)`
- [ ] `src/application/dtos/document-request.dto.ts` — `CreateDocumentRequestDTO`, `UpdateDocumentRequestDTO`, `DocumentRequestResponseDTO`
- [ ] `src/application/use-cases/document-request/get-document-requests.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/document-request/get-document-request.use-case.ts` — by id + `NotFoundError`
- [ ] `src/application/use-cases/document-request/create-document-request.use-case.ts` — assign UUID + server status/reference

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/document-request.schema.ts` — `document_requests` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/document-request.pg.repository.ts` — implement list/find/create + count
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/document-request.controller.ts` — `getDocumentRequests`, `getDocumentRequest`, `createDocumentRequest`
- [ ] `src/presentation/routes/document-request.routes.ts` — `GET /document-requests`, `GET /document-requests/:id`, `POST /document-requests`, `validate(CreateDocumentRequestDTO)` on POST

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add DocumentRequest repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/document-requests', documentRequestRouter)`

---

## Feature: Complaint
> Routes: `GET /complaints` · `POST /complaints`
> Depends on: StudentProfile

### Step 1 — Core
- [ ] `src/core/entities/complaint.entity.ts` — plain `Complaint` type

### Step 2 — Application
- [ ] `src/application/repositories/complaint.repository.ts` — `findAll(pagination)`, `create(input)`
- [ ] `src/application/dtos/complaint.dto.ts` — `CreateComplaintDTO`, `UpdateComplaintDTO`, `ComplaintResponseDTO`
- [ ] `src/application/use-cases/complaint/get-complaints.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/complaint/create-complaint.use-case.ts` — assign UUID + default status

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/complaint.schema.ts` — `complaints` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/complaint.pg.repository.ts` — implement list/create + count
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/complaint.controller.ts` — `getComplaints`, `createComplaint`
- [ ] `src/presentation/routes/complaint.routes.ts` — `GET /complaints`, `POST /complaints`, `validate(CreateComplaintDTO)` on POST

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add Complaint repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/complaints', complaintRouter)`

---

## Feature: EvaluationSubmission
> Routes: `GET /evaluations` · `POST /evaluations`
> Depends on: StudentProfile, Course

### Step 1 — Core
- [ ] `src/core/entities/evaluation-submission.entity.ts` — plain `EvaluationSubmission` type

### Step 2 — Application
- [ ] `src/application/repositories/evaluation-submission.repository.ts` — `findAll(pagination)`, `create(input)`
- [ ] `src/application/dtos/evaluation-submission.dto.ts` — `CreateEvaluationSubmissionDTO`, `UpdateEvaluationSubmissionDTO`, `EvaluationSubmissionResponseDTO`
- [ ] `src/application/use-cases/evaluation-submission/get-evaluation-submissions.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/evaluation-submission/create-evaluation-submission.use-case.ts` — assign UUID + submitted timestamp

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/evaluation-submission.schema.ts` — `evaluation_submissions` table + row/insert exports
- [ ] `src/infrastructure/db/repositories/evaluation-submission.pg.repository.ts` — implement list/create + count
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/evaluation-submission.controller.ts` — `getEvaluationSubmissions`, `createEvaluationSubmission`
- [ ] `src/presentation/routes/evaluation-submission.routes.ts` — `GET /evaluations`, `POST /evaluations`, `validate(CreateEvaluationSubmissionDTO)` on POST

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add EvaluationSubmission repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/evaluations', evaluationSubmissionRouter)`

---

## Feature: Enrollment
> Routes: `GET /enrollments` · `GET /enrollments/:id` · `POST /enrollments` · `PUT /enrollments/:id` · `DELETE /enrollments/:id`
> Depends on: StudentProfile, Course

### Step 1 — Core
- [ ] `src/core/entities/enrollment.entity.ts` — plain `Enrollment` type (`studentId` vs `studentIdNumber`) // clarify before implementing

### Step 2 — Application
- [ ] `src/application/repositories/enrollment.repository.ts` — `findAll(pagination)`, `findById(id)`, `create(input)`, `update(id, patch)`, `delete(id)`
- [ ] `src/application/dtos/enrollment.dto.ts` — `CreateEnrollmentDTO`, `UpdateEnrollmentDTO`, `EnrollmentResponseDTO`
- [ ] `src/application/use-cases/enrollment/get-enrollments.use-case.ts` — paginated list, return `{ data, total }`
- [ ] `src/application/use-cases/enrollment/get-enrollment.use-case.ts` — by id + `NotFoundError`
- [ ] `src/application/use-cases/enrollment/create-enrollment.use-case.ts` — assign UUID, compute/validate credits and tuition
- [ ] `src/application/use-cases/enrollment/update-enrollment.use-case.ts` — find first, apply partial, save
- [ ] `src/application/use-cases/enrollment/delete-enrollment.use-case.ts` — find first, delete

### Step 3 — Infrastructure
- [ ] `src/infrastructure/db/schema/enrollment.schema.ts` — `enrollments` table + join/pivot for `courseIds` mapping
- [ ] `src/infrastructure/db/repositories/enrollment.pg.repository.ts` — implement full CRUD + count + course linkage
- [ ] `drizzle-kit generate`
- [ ] `drizzle-kit migrate`

### Step 4 — Presentation
- [ ] `src/presentation/controllers/enrollment.controller.ts` — `getEnrollments`, `getEnrollment`, `createEnrollment`, `updateEnrollment`, `deleteEnrollment`
- [ ] `src/presentation/routes/enrollment.routes.ts` — wire all routes, `validate(CreateEnrollmentDTO)` on POST, `validate(UpdateEnrollmentDTO)` on PUT

### Step 5 — Wire (do now)
- [ ] `src/container.ts` — add Enrollment repo, use cases, controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/enrollments', enrollmentRouter)`

---

## Phase N — Auth

- [ ] `src/application/dtos/auth.dto.ts` — `LoginDto` (studentId, password, keepLoggedIn)
- [ ] `src/application/use-cases/auth/login.use-case.ts` — lookup user, verify password/hash, throw `UnauthorizedError`, return JWT
- [ ] `src/presentation/middleware/auth.middleware.ts` — verify bearer JWT, attach `req.user`, throw `UnauthorizedError` on invalid/missing token
- [ ] `src/presentation/controllers/auth.controller.ts` — `login`
- [ ] `src/presentation/routes/auth.routes.ts` — `POST /login` (no auth middleware)
- [ ] `src/container.ts` — add auth use case and controller export
- [ ] `src/app.ts` — mount `app.use('/api/v1/auth', authRouter)`

---

## Final Phase — Integration Verification

- [ ] `src/container.ts` — verify every entity has repo → use case(s) → controller chain
- [ ] `src/app.ts` — verify every router is mounted and `error.middleware` is last
- [ ] `npm run typecheck`
- [ ] `npm run test`
- [ ] Smoke test `GET /api/v1/programs` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/programs/:id` unknown id → `404 NOT_FOUND`
- [ ] Smoke test `GET /api/v1/students/:id` unknown id → `404 NOT_FOUND`
- [ ] Smoke test `PUT /api/v1/students/:id` invalid body → `400 VALIDATION_ERROR`
- [ ] Smoke test `GET /api/v1/library-books` → `200` + envelope + `meta`
- [ ] Smoke test `PUT /api/v1/library-books/:id` partial body → `200`
- [ ] Smoke test `GET /api/v1/courses` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/schedule` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/grades` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/transactions` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/document-requests` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/document-requests/:id` unknown id → `404 NOT_FOUND`
- [ ] Smoke test `POST /api/v1/document-requests` valid body → `201`
- [ ] Smoke test `POST /api/v1/document-requests` invalid body → `400 VALIDATION_ERROR`
- [ ] Smoke test `GET /api/v1/complaints` → `200` + envelope + `meta`
- [ ] Smoke test `POST /api/v1/complaints` valid body → `201`
- [ ] Smoke test `POST /api/v1/complaints` invalid body → `400 VALIDATION_ERROR`
- [ ] Smoke test `GET /api/v1/evaluations` → `200` + envelope + `meta`
- [ ] Smoke test `POST /api/v1/evaluations` valid body → `201`
- [ ] Smoke test `POST /api/v1/evaluations` invalid body → `400 VALIDATION_ERROR`
- [ ] Smoke test `GET /api/v1/enrollments` → `200` + envelope + `meta`
- [ ] Smoke test `GET /api/v1/enrollments/:id` unknown id → `404 NOT_FOUND`
- [ ] Smoke test `POST /api/v1/enrollments` valid body → `201`
- [ ] Smoke test `POST /api/v1/enrollments` invalid body → `400 VALIDATION_ERROR`
- [ ] Smoke test `PUT /api/v1/enrollments/:id` partial body → `200`
- [ ] Smoke test `DELETE /api/v1/enrollments/:id` → `200` with `data: null`
- [ ] Smoke test `POST /api/v1/auth/login` invalid creds → `401 UNAUTHORIZED`
