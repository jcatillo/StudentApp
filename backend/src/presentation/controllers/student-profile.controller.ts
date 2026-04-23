import type { NextFunction, Request, Response } from "express";

import type { GetStudentProfileUseCase } from "@/application/use-cases/student-profile/get-student-profile.use-case";
import type { UpdateStudentProfileUseCase } from "@/application/use-cases/student-profile/update-student-profile.use-case";

import type { StudentRepository } from "@/application/repositories/student.repository";

export class StudentProfileController {
  constructor(
    private readonly getStudentProfileUseCase: GetStudentProfileUseCase,
    private readonly updateStudentProfileUseCase: UpdateStudentProfileUseCase,
    private readonly studentRepo: StudentRepository,
  ) {}

  listStudents = async (_req: Request, res: Response, next: NextFunction) => {
    try {
      const students = await this.studentRepo.findAll();
      res.status(200).json({ success: true, data: students });
    } catch (err) {
      next(err);
    }
  };

  private getIdOrFail(req: Request): string {
    const { id } = req.params;
    if (!id || Array.isArray(id)) {
      throw new Error("Invalid student profile id parameter");
    }
    return id;
  }

  getStudentProfile = async (req: Request, res: Response, next: NextFunction) => {
    try {
      const profile = await this.getStudentProfileUseCase.execute(this.getIdOrFail(req));
      res.status(200).json({ success: true, data: profile });
    } catch (err) {
      next(err);
    }
  };

  updateStudentProfile = async (req: Request, res: Response, next: NextFunction) => {
    try {
      const profile = await this.updateStudentProfileUseCase.execute(this.getIdOrFail(req), req.body);
      res.status(200).json({ success: true, data: profile });
    } catch (err) {
      next(err);
    }
  };
}
