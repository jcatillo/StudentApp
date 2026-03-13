package com.example.studentapp.ui.screens.enrollment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.ContactPhone
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentBottomSheet
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentCourseCard
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentCourseSearchBar
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentHeaderSection
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentPrimaryButton
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentSectionHeader
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentSelectField
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentTextField
import com.example.studentapp.ui.screens.enrollment.models.EnrollableCourse
import com.example.studentapp.ui.screens.enrollment.models.EnrollmentStep
import com.example.studentapp.ui.screens.enrollment.models.buildEnrollableCourses
import com.example.studentapp.ui.screens.enrollment.models.filterEnrollableCourses
import com.example.studentapp.ui.theme.StudentAppTheme

@Composable
fun EnrollmentScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by rememberSaveable { mutableStateOf(EnrollmentStep.Courses) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val courses = remember { buildEnrollableCourses() }
    var selectedCourseCodes by rememberSaveable {
        mutableStateOf(
            courses.filter { it.isInitiallySelected }.map { it.code }
        )
    }

    var fullName by rememberSaveable { mutableStateOf("") }
    var studentId by rememberSaveable { mutableStateOf("") }
    var emailAddress by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var emergencyContactName by rememberSaveable { mutableStateOf("") }
    var relationship by rememberSaveable { mutableStateOf("Parent") }
    var emergencyPhone by rememberSaveable { mutableStateOf("") }

    val filteredCourses = remember(searchQuery, courses) {
        filterEnrollableCourses(
            courses = courses,
            searchQuery = searchQuery
        )
    }
    val selectedCourses = remember(selectedCourseCodes, courses) {
        courses.filter { course -> selectedCourseCodes.contains(course.code) }
    }
    val selectedCredits = selectedCourses.sumOf { it.units }
    val estimatedTuition = selectedCourses.sumOf { it.tuition }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(EnrollmentScreenColors.BackgroundLight),
        containerColor = EnrollmentScreenColors.BackgroundLight,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            EnrollmentHeaderSection(
                step = currentStep,
                onBackClick = {
                    if (currentStep == EnrollmentStep.PersonalInfo) {
                        currentStep = EnrollmentStep.Courses
                    } else {
                        onBackClick()
                    }
                }
            )
        },
        bottomBar = {
            if (currentStep == EnrollmentStep.Courses) {
                EnrollmentBottomSheet(
                    selectedCredits = selectedCredits,
                    maxCredits = 18,
                    estimatedTuition = estimatedTuition,
                    onNextClick = {
                        currentStep = EnrollmentStep.PersonalInfo
                    }
                )
            }
        }
    ) { innerPadding ->
        when (currentStep) {
            EnrollmentStep.Courses -> {
                EnrollmentCoursesStepContent(
                    courses = filteredCourses,
                    selectedCourseCodes = selectedCourseCodes,
                    searchQuery = searchQuery,
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = innerPadding.calculateTopPadding() + 24.dp,
                        end = 16.dp,
                        bottom = innerPadding.calculateBottomPadding() + 16.dp
                    ),
                    onSearchQueryChange = { searchQuery = it },
                    onCourseToggle = { course ->
                        if (course.isLocked) return@EnrollmentCoursesStepContent

                        selectedCourseCodes = if (selectedCourseCodes.contains(course.code)) {
                            selectedCourseCodes - course.code
                        } else {
                            selectedCourseCodes + course.code
                        }
                    }
                )
            }

            EnrollmentStep.PersonalInfo -> {
                EnrollmentPersonalInfoStepContent(
                    fullName = fullName,
                    studentId = studentId,
                    emailAddress = emailAddress,
                    phoneNumber = phoneNumber,
                    emergencyContactName = emergencyContactName,
                    relationship = relationship,
                    emergencyPhone = emergencyPhone,
                    contentPadding = PaddingValues(
                        start = 24.dp,
                        top = innerPadding.calculateTopPadding() + 24.dp,
                        end = 24.dp,
                        bottom = 32.dp
                    ),
                    onFullNameChange = { fullName = it },
                    onStudentIdChange = { studentId = it },
                    onEmailAddressChange = { emailAddress = it },
                    onPhoneNumberChange = { phoneNumber = it },
                    onEmergencyContactNameChange = { emergencyContactName = it },
                    onRelationshipChange = { relationship = it },
                    onEmergencyPhoneChange = { emergencyPhone = it }
                )
            }
        }
    }
}

@Composable
fun EnrollmentCoursesStepContent(
    courses: List<EnrollableCourse>,
    selectedCourseCodes: List<String>,
    searchQuery: String,
    contentPadding: PaddingValues,
    onSearchQueryChange: (String) -> Unit,
    onCourseToggle: (EnrollableCourse) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Select Courses",
                color = EnrollmentScreenColors.Slate900,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Fall Semester 2024 \u2022 Year 3",
                modifier = Modifier.padding(top = 4.dp),
                color = EnrollmentScreenColors.Slate500,
                fontSize = 14.sp
            )
        }

        item {
            EnrollmentCourseSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(
            items = courses,
            key = { it.code }
        ) { course ->
            EnrollmentCourseCard(
                course = course,
                isSelected = selectedCourseCodes.contains(course.code),
                onClick = { onCourseToggle(course) }
            )
        }
    }
}

@Composable
fun EnrollmentPersonalInfoStepContent(
    fullName: String,
    studentId: String,
    emailAddress: String,
    phoneNumber: String,
    emergencyContactName: String,
    relationship: String,
    emergencyPhone: String,
    contentPadding: PaddingValues,
    onFullNameChange: (String) -> Unit,
    onStudentIdChange: (String) -> Unit,
    onEmailAddressChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onEmergencyContactNameChange: (String) -> Unit,
    onRelationshipChange: (String) -> Unit,
    onEmergencyPhoneChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            EnrollmentSectionHeader(
                title = "Identification",
                icon = Icons.Outlined.Badge
            )
            EnrollmentTextField(
                label = "Full Legal Name",
                value = fullName,
                onValueChange = onFullNameChange,
                placeholder = "Johnathan Doe"
            )
            EnrollmentTextField(
                label = "Student ID Number",
                value = studentId,
                onValueChange = onStudentIdChange,
                placeholder = "STU-2024-XXXX",
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        item {
            EnrollmentSectionHeader(
                title = "Contact Details",
                icon = Icons.Outlined.ContactMail
            )
            EnrollmentTextField(
                label = "Email Address",
                value = emailAddress,
                onValueChange = onEmailAddressChange,
                placeholder = "j.doe@example.com"
            )
            EnrollmentTextField(
                label = "Phone Number",
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                placeholder = "+1 (555) 000-0000",
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        item {
            EnrollmentSectionHeader(
                title = "Emergency Contact",
                // HTML uses the Material Symbol `emergency_home`; Compose Material icons do not
                // expose an exact match, so `ContactPhone` is the closest official equivalent.
                icon = Icons.Outlined.ContactPhone
            )
            EnrollmentTextField(
                label = "Contact Person Name",
                value = emergencyContactName,
                onValueChange = onEmergencyContactNameChange,
                placeholder = "Jane Doe"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                EnrollmentSelectField(
                    label = "Relationship",
                    value = relationship,
                    options = listOf("Parent", "Guardian", "Spouse", "Other"),
                    onValueSelected = onRelationshipChange,
                    modifier = Modifier.weight(1f)
                )
                EnrollmentTextField(
                    label = "Emergency Phone",
                    value = emergencyPhone,
                    onValueChange = onEmergencyPhoneChange,
                    placeholder = "+1 (555) 999-9999",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            EnrollmentPrimaryButton(
                text = "Proceed to Payment",
                icon = Icons.Outlined.Payments,
                onClick = {}
            )
            Text(
                text = "By proceeding, you agree to our terms of enrollment.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = EnrollmentScreenColors.MutedText,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnrollmentScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        EnrollmentScreen(onBackClick = {})
    }
}
