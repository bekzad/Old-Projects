package labs.o.sokobanmvcpattern

class Controller(viewer: MainActivity) {
    private val model = Model(viewer)

    fun getModel(): Model {
        println("Controller model ${model}")
        return model
    }
}