CREATE TYPE "public"."document_type" AS ENUM('TOR', 'GOOD_MORAL', 'COE');--> statement-breakpoint
CREATE TYPE "public"."request_status" AS ENUM('PENDING', 'PROCESSING', 'READY', 'COMPLETED', 'REJECTED');--> statement-breakpoint
CREATE TYPE "public"."program_category" AS ENUM('Undergraduate', 'Postgraduate');--> statement-breakpoint
CREATE TYPE "public"."registration_status" AS ENUM('Enrolled', 'Completed', 'Waitlisted');--> statement-breakpoint
CREATE TYPE "public"."payment_method" AS ENUM('CASH', 'GCASH', 'CREDIT_CARD', 'BANK_TRANSFER', 'NONE');--> statement-breakpoint
CREATE TYPE "public"."transaction_status" AS ENUM('PENDING', 'COMPLETED', 'FAILED');--> statement-breakpoint
CREATE TYPE "public"."transaction_type" AS ENUM('CHARGE', 'PAYMENT');--> statement-breakpoint
CREATE TABLE "books" (
	"id" text PRIMARY KEY NOT NULL,
	"title" text NOT NULL,
	"author" text NOT NULL,
	"isbn" text NOT NULL,
	"publisher" text NOT NULL,
	"publication_year" integer NOT NULL,
	"category" text NOT NULL,
	"total_copies" integer NOT NULL,
	"available_copies" integer NOT NULL,
	"location" text NOT NULL,
	"cover_image_url" text,
	"external_link" text,
	"created_at" timestamp with time zone DEFAULT now() NOT NULL,
	"updated_at" timestamp with time zone DEFAULT now() NOT NULL,
	CONSTRAINT "books_isbn_unique" UNIQUE("isbn")
);
--> statement-breakpoint
CREATE TABLE "borrow_records" (
	"id" text PRIMARY KEY NOT NULL,
	"user_id" text NOT NULL,
	"book_id" text NOT NULL,
	"borrowed_at" timestamp with time zone DEFAULT now() NOT NULL,
	"due_date" timestamp with time zone NOT NULL,
	"returned_at" timestamp with time zone
);
--> statement-breakpoint
CREATE TABLE "document_requests" (
	"id" text PRIMARY KEY NOT NULL,
	"student_id" text NOT NULL,
	"document_type" "document_type" NOT NULL,
	"purpose" text NOT NULL,
	"copies" integer DEFAULT 1 NOT NULL,
	"request_status" "request_status" DEFAULT 'PENDING' NOT NULL,
	"notes" text,
	"requested_at" timestamp with time zone DEFAULT now() NOT NULL,
	"updated_at" timestamp with time zone DEFAULT now() NOT NULL
);
--> statement-breakpoint
CREATE TABLE "programs" (
	"id" varchar(128) PRIMARY KEY NOT NULL,
	"title" varchar(255) NOT NULL,
	"category" "program_category" NOT NULL,
	"status" varchar(100),
	"duration" varchar(100),
	"description" text,
	"prospectus_url" varchar(500),
	"created_at" timestamp DEFAULT now()
);
--> statement-breakpoint
CREATE TABLE "student_profiles" (
	"id" text PRIMARY KEY NOT NULL,
	"full_name" text NOT NULL,
	"email_address" text NOT NULL,
	"phone_number" text NOT NULL,
	"account_label" text NOT NULL,
	"program_summary" text NOT NULL,
	"two_factor_status" text NOT NULL,
	"emergency_contact_name" text NOT NULL,
	"emergency_contact_relationship" text NOT NULL,
	"emergency_contact_phone_number" text NOT NULL,
	"email_notifications" boolean DEFAULT false NOT NULL,
	"sms_notifications" boolean DEFAULT false NOT NULL,
	"system_alerts" boolean DEFAULT false NOT NULL,
	"created_at" timestamp with time zone DEFAULT now() NOT NULL,
	"updated_at" timestamp with time zone DEFAULT now() NOT NULL
);
--> statement-breakpoint
CREATE TABLE "sections" (
	"id" varchar(128) PRIMARY KEY NOT NULL,
	"subject_id" varchar(128) NOT NULL,
	"term" varchar(128) NOT NULL,
	"instructor_name" varchar(255) NOT NULL,
	"time_slots" varchar(255) NOT NULL,
	"location" varchar(255) NOT NULL
);
--> statement-breakpoint
CREATE TABLE "subject_registrations" (
	"id" uuid PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
	"student_id" varchar(128) NOT NULL,
	"section_id" varchar(128) NOT NULL,
	"status" "registration_status" NOT NULL,
	"progress_percentage" numeric(5, 2),
	"created_at" timestamp DEFAULT now()
);
--> statement-breakpoint
CREATE TABLE "subjects" (
	"id" varchar(128) PRIMARY KEY NOT NULL,
	"title" varchar(255) NOT NULL,
	"units" integer NOT NULL
);
--> statement-breakpoint
CREATE TABLE "transactions" (
	"id" text PRIMARY KEY NOT NULL,
	"student_id" text NOT NULL,
	"transaction_type" "transaction_type" NOT NULL,
	"amount" integer NOT NULL,
	"payment_method" "payment_method" DEFAULT 'NONE' NOT NULL,
	"transaction_status" "transaction_status" DEFAULT 'PENDING' NOT NULL,
	"reference_id" text NOT NULL,
	"description" text NOT NULL,
	"created_at" timestamp with time zone DEFAULT now() NOT NULL
);
--> statement-breakpoint
ALTER TABLE "borrow_records" ADD CONSTRAINT "borrow_records_book_id_books_id_fk" FOREIGN KEY ("book_id") REFERENCES "public"."books"("id") ON DELETE cascade ON UPDATE no action;--> statement-breakpoint
ALTER TABLE "document_requests" ADD CONSTRAINT "document_requests_student_id_students_id_fk" FOREIGN KEY ("student_id") REFERENCES "public"."students"("id") ON DELETE no action ON UPDATE no action;--> statement-breakpoint
ALTER TABLE "transactions" ADD CONSTRAINT "transactions_student_id_students_id_fk" FOREIGN KEY ("student_id") REFERENCES "public"."students"("id") ON DELETE no action ON UPDATE no action;