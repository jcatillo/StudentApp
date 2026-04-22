import { z } from "zod";

export const CreateStudentProfileDto = z.object({
  fullName: z.string().min(1),
  emailAddress: z.string().email(),
  phoneNumber: z.string().min(1),
  accountLabel: z.string().min(1),
  programSummary: z.string().min(1),
  twoFactorStatus: z.enum(["Disabled", "PendingVerification", "Enabled"]),
  emergencyContactName: z.string().min(1),
  emergencyContactRelationship: z.string().min(1),
  emergencyContactPhoneNumber: z.string().min(1),
  emailNotifications: z.boolean(),
  smsNotifications: z.boolean(),
  systemAlerts: z.boolean(),
});

export const UpdateStudentProfileDto = CreateStudentProfileDto.partial();

export type CreateStudentProfileInput = z.infer<typeof CreateStudentProfileDto>;
export type UpdateStudentProfileInput = z.infer<typeof UpdateStudentProfileDto>;
