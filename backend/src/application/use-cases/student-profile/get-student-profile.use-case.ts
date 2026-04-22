import type { StudentProfileRepository } from "@/application/repositories/student-profile.repository";
import type { StudentProfile } from "@/core/entities/student-profile.entity";
import { NotFoundError } from "@/core/errors/domain.error";

export class GetStudentProfileUseCase {
  constructor(private readonly studentProfileRepo: StudentProfileRepository) {}

  async execute(id: string): Promise<StudentProfile> {
    const profile = await this.studentProfileRepo.findById(id);
    if (!profile) {
      throw new NotFoundError("StudentProfile");
    }

    return profile;
  }
}
