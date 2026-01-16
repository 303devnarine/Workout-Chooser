import kotlin.random.Random

fun main() {
    println("Welcome to the Workout Chooser")
    var Workouts: ArrayList<Workout> = ArrayList<Workout>()

    println("Add Your Workouts: ")

    var continu = true
    do {
        var newWorkout: Workout = Workout()

        print("What is the workout? |: ")
        newWorkout.setName(readln())

        print("What weight is used? |: ")
        newWorkout.setWeightBest(readln().toInt())

        print("How many reps? |: ")
        newWorkout.setReps(readln().toInt())

        print("How many sets? |: ")
        newWorkout.setSets(readln().toInt())

        print("How long (in mins.) does it take you to do this workout? |: ")
        newWorkout.setTimeToDo(readln().toInt())

        println("Add what parts of the body will be worked: (type 'no more' to stop adding)")

        do {
            val part: String = readln()
            if (part != "no more") newWorkout.addPartsWorked(part)
        } while (part.lowercase().equals("no more") == false)

        Workouts.add(newWorkout)

        println("Add more workouts? (Y/N)")
        continu = if (readln().lowercase() == "y") true else false

    } while (continu == true)

    // once all workout are added in
    println("What body part(s) do you wish to work on today? (type 'no more' to stop adding)")
    var todaysBodyParts: ArrayList<String> = ArrayList<String>()

    do {
        var part: String = readln()
        if (part != "no more") todaysBodyParts.add(part)
    } while (part.lowercase().equals("no more") == false)

    var workoutsWithTarget: ArrayList<Workout> = ArrayList<Workout>()

    for (i in 0..Workouts.size - 1) {
        var thisWorkoutParts = Workouts[i].getPartsWorked();

        for (j in 0..todaysBodyParts.size - 1) {
            if (!(workoutsWithTarget.contains(Workouts[i])) && thisWorkoutParts.contains(todaysBodyParts[j]))
                workoutsWithTarget.add(Workouts[i])
        }
    }

    if (workoutsWithTarget.size > 2) {
        var chosenWorkouts: ArrayList<Workout> = ArrayList<Workout>()
        do {
            var chosenWorkout = workoutsWithTarget[Random.nextInt(workoutsWithTarget.size)]
            if (!chosenWorkouts.contains(chosenWorkout)) chosenWorkouts.add(chosenWorkout)
        } while (chosenWorkouts.size < 3)
        println("Here are 3 workouts for you!")

        println("1] ${chosenWorkouts[0]} \n")

        println("2] ${chosenWorkouts[1]} \n")

        println("3] ${chosenWorkouts[2]} \n")
    } else {
        for (i in 0..workoutsWithTarget.size - 1) println("${i+1}] ${workoutsWithTarget[i]}\n")
    }
}

class Workout {
    // defines attributes of workout
    private lateinit var name: String;
    private var weightBest = 0
    private var reps = 0
    private var sets = 0
    private var timeToDo = 0 // in mins

    private var partsWorked: ArrayList<String> = ArrayList<String>()

    // getters & setters for each attribute
    fun getName(): String     { return name }
    fun setName(name: String) { this.name = name }

    fun getWeight(): Int           { return this.weightBest }
    fun setWeightBest(weight: Int) { this.weightBest = weight; }

    fun getReps(): Int     { return this.reps }
    fun setReps(reps: Int) { this.reps = reps }

    fun getSets(): Int     { return this.sets }
    fun setSets(sets: Int) { this.sets = sets }

    fun getTimeToDo(): Int         { return this.timeToDo }
    fun setTimeToDo(timeToDo: Int) { this.timeToDo = timeToDo }

    fun getPartsWorked(): ArrayList<String>  { return this.partsWorked }
    fun addPartsWorked(muscle: String)       { partsWorked.add(muscle) }

    // toString
    override fun toString(): String { return "${this.getName()} | Use ${this.getWeight()} lb\n" +
                                             "${this.getReps()} reps for ${this.getSets()} sets\n" +
                                             "${this.getTimeToDo()} mins.\n" +
                                             "Parts Worked: ${this.getPartsWorked()}" }
}