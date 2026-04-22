import type { UpdateStudentProfileInput } from "@/application/dtos/student-profile.dto";
import type { StudentProfileRepository } from "@/application/repositories/student-profile.repository";
import type { StudentProfile } from "@/core/entities/student-profile.entity";
import { NotFoundError } from "@/core/errors/domain.error";

export class UpdateStudentProfileUseCase {
  constructor(private readonly studentProfileRepo: StudentProfileRepository) {}

  async execute(id: string, input: UpdateStudentProfileInput): Promise<StudentProfile> {
    const existing = await this.studentProfileRepo.findById(id);
    if (!existing) {
      throw new NotFoundError("StudentProfile");
    }

    const patch: Partial<StudentProfile> = { updatedAt: new Date() };

    if (input.fullName !== undefined) patch.fullName = input.fullName;
    if (input.emailAddress !== undefined) patch.emailAddress = input.emailAddress;
    if (input.phoneNumber !== undefined) patch.phoneNumber = input.phoneNumber;
    if (input.accountLabel !== undefined) patch.accountLabel = input.accountLabel;
    if (input.programSummary !== undefined) patch.programSummary = input.programSummary;
    if (input.twoFactorStatus !== undefined) patch.twoFactorStatus = input.twoFactorStatus;
    if (input.emergencyContactName !== undefined)
      patch.emergencyContactName = input.emergencyContactName;
    if (input.emergencyContactRelationship !== undefined)
      patch.emergencyContactRelationship = input.emergencyContactRelationship;
    if (input.emergencyContactPhoneNumber !== undefined)
      patch.emergencyContactPhoneNumber = input.emergencyContactPhoneNumber;
    if (input.emailNotifications !== undefined) patch.emailNotifications = input.emailNotifications;
    if (input.smsNotifications !== undefined) patch.smsNotifications = input.smsNotifications;
    if (input.systemAlerts !== undefined) patch.systemAlerts = input.systemAlerts;

    return this.studentProfileRepo.update(id, patch);
  }
}
