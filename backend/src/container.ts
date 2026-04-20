import { CreateStudentUseCase } from "@/application/use-cases/student/create-student.use-case";
import { DeleteStudentUseCase } from "@/application/use-cases/student/delete-student.use-case";
import { GetStudentUseCase } from "@/application/use-cases/student/get-student.use-case";
import { UpdateStudentUseCase } from "@/application/use-cases/student/update-student.use-case";
import { db } from "@/infrastructure/db/client";
import { StudentPgRepository } from "@/infrastructure/db/repositories/student.pg.repository";
import { StudentController } from "@/presentation/controllers/student.controller";

import { BookPgRepository } from "@/infrastructure/db/repositories/book.pg.repository";
import { BorrowRecordPgRepository } from "@/infrastructure/db/repositories/borrow-record.pg.repository";
import { BorrowBookUseCase } from "@/application/use-cases/book/borrow-book.use-case";
import { ReturnBookUseCase } from "@/application/use-cases/book/return-book.use-case";
import { BookController } from "@/presentation/controllers/book.controller";

// --- 1. Import Document Classes ---
import { DocumentRequestPgRepository } from "@/infrastructure/db/repositories/document-request.pg.repository";
import { CreateDocumentRequestUseCase } from "@/application/use-cases/document/create-request.use-case";
import { DocumentController } from "@/presentation/controllers/document.controller";

// --- Student Wiring ---
const studentRepo = new StudentPgRepository(db);

const createStudentUseCase = new CreateStudentUseCase(studentRepo);
const getStudentUseCase = new GetStudentUseCase(studentRepo);
const updateStudentUseCase = new UpdateStudentUseCase(studentRepo);
const deleteStudentUseCase = new DeleteStudentUseCase(studentRepo);

export const studentController = new StudentController(
  createStudentUseCase,
  getStudentUseCase,
  updateStudentUseCase,
  deleteStudentUseCase,
);

// --- Book Wiring ---
const bookRepo = new BookPgRepository(db);
const borrowRecordRepo = new BorrowRecordPgRepository(db);

const borrowBookUseCase = new BorrowBookUseCase(bookRepo, borrowRecordRepo);
const returnBookUseCase = new ReturnBookUseCase(bookRepo, borrowRecordRepo);

export const bookController = new BookController(
  borrowBookUseCase,
  returnBookUseCase
);

// --- Document Request Wiring ---
// 2. Instantiate Repository (The Engine)
const documentRepo = new DocumentRequestPgRepository(db);

// 3. Instantiate Use Case (The Brain)
const createDocumentRequestUseCase = new CreateDocumentRequestUseCase(documentRepo);

export const documentController = new DocumentController(
  createDocumentRequestUseCase
);