export type StudentProfile = {
  id: string;
  fullName: string;
  emailAddress: string;
  phoneNumber: string;
  accountLabel: string;
  programSummary: string;
  twoFactorStatus: "Disabled" | "PendingVerification" | "Enabled";
  emergencyContactName: string;
  emergencyContactRelationship: string;
  emergencyContactPhoneNumber: string;
  emailNotifications: boolean;
  smsNotifications: boolean;
  systemAlerts: boolean;
  createdAt: Date;
  updatedAt: Date;
};
