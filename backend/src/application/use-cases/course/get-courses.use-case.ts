import type { CourseRepository } from '@/application/repositories/course.repository';

export type GetCoursesInput = {
  page: number;
  limit: number;
  programId?: string;
  code?: string;
  status?: string;
};

export class GetCoursesUseCase {
  constructor(private readonly courseRepo: CourseRepository) {}

  async execute(input: GetCoursesInput) {
    const { page, limit, programId, code, status } = input;
    const filter: { programId?: string; code?: string; status?: string } = {};
    if (programId !== undefined) filter.programId = programId;
    if (code !== undefined) filter.code = code;
    if (status !== undefined) filter.status = status;

    return this.courseRepo.findAll({ page, limit }, filter);
  }
}
