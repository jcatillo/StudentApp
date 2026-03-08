package com.example.studentapp.domain.usecase

import com.example.studentapp.domain.model.AcademicOverview
import com.example.studentapp.domain.model.AcademicService
import com.example.studentapp.domain.model.SupportMessage

class GetAcademicOverviewUseCase {
    operator fun invoke(): AcademicOverview {
        return AcademicOverview(
            studentName = "James Anderson",
            programName = "Computer Science",
            yearLevel = "Year 3",
            services = listOf(
                AcademicService(
                    code = "PR",
                    title = "Programs",
                    description = "Review degree tracks and curriculum requirements."
                ),
                AcademicService(
                    code = "CR",
                    title = "Courses",
                    description = "Check current offerings and section details."
                ),
                AcademicService(
                    code = "PX",
                    title = "Prospectus",
                    description = "Open your official plan of study for the term."
                ),
                AcademicService(
                    code = "EN",
                    title = "Enrollment",
                    description = "Manage registration windows and enrollment status."
                ),
                AcademicService(
                    code = "AD",
                    title = "Adjustments",
                    description = "Submit add, drop, or correction requests."
                ),
                AcademicService(
                    code = "GR",
                    title = "Grades",
                    description = "View academic standing and released grades."
                ),
                AcademicService(
                    code = "EV",
                    title = "Evaluations",
                    description = "Complete faculty and course evaluations."
                ),
                AcademicService(
                    code = "SL",
                    title = "Study Load",
                    description = "Inspect units, class load, and weekly distribution."
                )
            ),
            supportMessage = SupportMessage(
                title = "Need Assistance?",
                description = "Contact the academic office for enrollment support or technical issues.",
                actionLabel = "Contact Support"
            )
        )
    }
}
