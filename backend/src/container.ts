import { LoginUseCase } from "@/application/use-cases/auth/login.use-case";
import { GetStudentProfileUseCase } from "@/application/use-cases/student-profile/get-student-profile.use-case";
import { UpdateStudentProfileUseCase } from "@/application/use-cases/student-profile/update-student-profile.use-case";
import { db } from "@/infrastructure/db/client";
import { StudentProfilePgRepository } from "@/infrastructure/db/repositories/student-profile.pg.repository";
import { StudentPgRepository } from "@/infrastructure/db/repositories/student.pg.repository";
import { AuthController } from "@/presentation/controllers/auth.controller";
import { StudentProfileController } from "@/presentation/controllers/student-profile.controller";

// --- Student/Auth Wiring ---
const studentRepo = new StudentPgRepository(db);
const loginUseCase = new LoginUseCase(studentRepo);

export const authController = new AuthController(loginUseCase);

const studentProfileRepo = new StudentProfilePgRepository(db);
const getStudentProfileUseCase = new GetStudentProfileUseCase(studentProfileRepo);
const updateStudentProfileUseCase = new UpdateStudentProfileUseCase(studentProfileRepo);

export const studentProfileController = new StudentProfileController(
  getStudentProfileUseCase,
  updateStudentProfileUseCase,
);
