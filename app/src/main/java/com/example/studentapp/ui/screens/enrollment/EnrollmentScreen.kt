package com.example.studentapp.ui.screens.enrollment

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentapp.ui.components.StudentBottomNavBar
import com.example.studentapp.ui.components.StudentBottomNavItem
import com.example.studentapp.ui.components.buildPrimaryBottomNavItems
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentBottomSheet
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentConfirmationStepContent
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentCourseStepContent
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentHeaderSection
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentPaymentStepContent
import com.example.studentapp.ui.screens.enrollment.components.EnrollmentPersonalInfoStepContent
import com.example.studentapp.ui.screens.enrollment.models.EnrollmentStep
import com.example.studentapp.ui.screens.enrollment.models.buildEnrollableCourses
import com.example.studentapp.ui.screens.enrollment.models.buildEnrollmentConfirmationCourses
import com.example.studentapp.ui.screens.enrollment.models.filterEnrollableCourses
import com.example.studentapp.ui.theme.StudentAppTheme

@Composable
fun EnrollmentScreen(
    navigationItems: List<StudentBottomNavItem>,
    selectedNavItemId: String,
    onBottomNavSelected: (StudentBottomNavItem) -> Unit,
    onBackClick: () -> Unit,
    onDownloadReceiptClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onAdjustmentClick: () -> Unit = {},
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
    val confirmationCourses = remember(selectedCourses) {
        buildEnrollmentConfirmationCourses(selectedCourses)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            AnimatedVisibility(
                visible = currentStep != EnrollmentStep.Confirmation,
                enter = fadeIn(animationSpec = tween(220)) +
                    slideInVertically(animationSpec = tween(280)) { -it / 4 },
                exit = fadeOut(animationSpec = tween(180)) +
                    slideOutVertically(animationSpec = tween(220)) { -it / 4 }
            ) {
                EnrollmentHeaderSection(
                    step = currentStep,
                    onBackClick = {
                        currentStep = previousEnrollmentStep(currentStep) ?: run {
                            onBackClick()
                            return@EnrollmentHeaderSection
                        }
                    }
                )
            }
        },
        bottomBar = {
            Column {
                AnimatedVisibility(
                    visible = currentStep == EnrollmentStep.Courses,
                    enter = fadeIn(animationSpec = tween(220)) +
                        slideInVertically(animationSpec = tween(280)) { it / 3 },
                    exit = fadeOut(animationSpec = tween(180)) +
                        slideOutVertically(animationSpec = tween(220)) { it / 3 }
                ) {
                    EnrollmentBottomSheet(
                        selectedCredits = selectedCredits,
                        maxCredits = 18,
                        estimatedTuition = estimatedTuition,
                        onNextClick = {
                            currentStep = EnrollmentStep.PersonalInfo
                        }
                    )
                }

                StudentBottomNavBar(
                    items = navigationItems,
                    selectedItemId = selectedNavItemId,
                    onItemSelected = onBottomNavSelected
                )
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = currentStep,
            transitionSpec = {
                val movingForward = targetState.stepNumber > initialState.stepNumber
                (
                    slideInHorizontally(
                        animationSpec = tween(320),
                        initialOffsetX = { fullWidth ->
                            if (movingForward) fullWidth / 3 else -fullWidth / 3
                        }
                    ) + fadeIn(animationSpec = tween(320))
                ).togetherWith(
                    slideOutHorizontally(
                        animationSpec = tween(280),
                        targetOffsetX = { fullWidth ->
                            if (movingForward) -fullWidth / 5 else fullWidth / 5
                        }
                    ) + fadeOut(animationSpec = tween(220))
                ).using(SizeTransform(clip = false))
            },
            label = "enrollment_step_transition"
        ) { step ->
            when (step) {
                EnrollmentStep.Courses -> {
                    EnrollmentCourseStepContent(
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
                            if (course.isLocked) return@EnrollmentCourseStepContent

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
                            bottom = innerPadding.calculateBottomPadding() + 32.dp
                        ),
                        onFullNameChange = { fullName = it },
                        onStudentIdChange = { studentId = it },
                        onEmailAddressChange = { emailAddress = it },
                        onPhoneNumberChange = { phoneNumber = it },
                        onEmergencyContactNameChange = { emergencyContactName = it },
                        onRelationshipChange = { relationship = it },
                        onEmergencyPhoneChange = { emergencyPhone = it },
                        onNextClick = { currentStep = EnrollmentStep.Payment }
                    )
                }

                EnrollmentStep.Payment -> {
                    EnrollmentPaymentStepContent(
                        selectedCourses = selectedCourses,
                        selectedCredits = selectedCredits,
                        estimatedTuition = estimatedTuition,
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
                            bottom = innerPadding.calculateBottomPadding() + 32.dp
                        ),
                        onConfirmClick = { currentStep = EnrollmentStep.Confirmation }
                    )
                }

                EnrollmentStep.Confirmation -> {
                    EnrollmentConfirmationStepContent(
                        courses = confirmationCourses,
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = innerPadding.calculateBottomPadding() + 32.dp
                        ),
                        onBackClick = { currentStep = EnrollmentStep.Payment },
                        onDownloadReceiptClick = onDownloadReceiptClick,
                        onHomeClick = onHomeClick,
                        onAdjustmentClick = onAdjustmentClick
                    )
                }
            }
        }
    }
}

private fun previousEnrollmentStep(step: EnrollmentStep): EnrollmentStep? {
    return when (step) {
        EnrollmentStep.Courses -> null
        EnrollmentStep.PersonalInfo -> EnrollmentStep.Courses
        EnrollmentStep.Payment -> EnrollmentStep.PersonalInfo
        EnrollmentStep.Confirmation -> EnrollmentStep.Payment
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnrollmentScreenPreview() {
    StudentAppTheme(dynamicColor = false) {
        EnrollmentScreen(
            navigationItems = buildPrimaryBottomNavItems(),
            selectedNavItemId = "academic",
            onBottomNavSelected = {},
            onBackClick = {}
        )
    }
}
