package com.example.a7minutesworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        var exerciseList = ArrayList<ExerciseModel>()
        val model1 = ExerciseModel(
            1,
            "Jumping jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        val model2 = ExerciseModel(
            1,
            "High Knees",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        val model3 = ExerciseModel(
            1,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false
        )
        val model4 = ExerciseModel(
            1,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        val model5 = ExerciseModel(
            1,
            "Push up",
            R.drawable.ic_push_up,
            false,
            false
        )
        val model6 = ExerciseModel(
            1,
            "Push up and rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        val model7 = ExerciseModel(
            1,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        val model8 = ExerciseModel(
            1,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        val model9 = ExerciseModel(
            1,
            "step up onto chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        val model10 = ExerciseModel(
            1,
            "triceps dips on chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        val model11 = ExerciseModel(
            1,
            "wall sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )

        exerciseList.add(model1)
        exerciseList.add(model2)
        exerciseList.add(model3)
        exerciseList.add(model4)
        exerciseList.add(model5)
        exerciseList.add(model6)
        exerciseList.add(model7)
        exerciseList.add(model8)
        exerciseList.add(model9)
        exerciseList.add(model10)
        exerciseList.add(model11)

        return exerciseList
    }
}