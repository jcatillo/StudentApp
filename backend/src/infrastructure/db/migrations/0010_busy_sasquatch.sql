ALTER TABLE "enrollment_courses" ADD CONSTRAINT "enrollment_courses_enrollment_id_course_id_pk" PRIMARY KEY("enrollment_id","course_id");--> statement-breakpoint
ALTER TABLE "grade_records" ADD COLUMN "remarks" varchar(255);--> statement-breakpoint
ALTER TABLE "courses" DROP COLUMN "progress";