import { boolean, pgTable, text, timestamp } from "drizzle-orm/pg-core";

export const studentProfiles = pgTable("student_profiles", {
  id: text("id").primaryKey(),
  fullName: text("full_name").notNull(),
  emailAddress: text("email_address").notNull(),
  phoneNumber: text("phone_number").notNull(),
  accountLabel: text("account_label").notNull(),
  programSummary: text("program_summary").notNull(),
  twoFactorStatus: text("two_factor_status").notNull(),
  emergencyContactName: text("emergency_contact_name").notNull(),
  emergencyContactRelationship: text("emergency_contact_relationship").notNull(),
  emergencyContactPhoneNumber: text("emergency_contact_phone_number").notNull(),
  emailNotifications: boolean("email_notifications").notNull().default(false),
  smsNotifications: boolean("sms_notifications").notNull().default(false),
  systemAlerts: boolean("system_alerts").notNull().default(false),
  createdAt: timestamp("created_at", { withTimezone: true }).defaultNow().notNull(),
  updatedAt: timestamp("updated_at", { withTimezone: true }).defaultNow().notNull(),
});

export type StudentProfileRow = typeof studentProfiles.$inferSelect;
