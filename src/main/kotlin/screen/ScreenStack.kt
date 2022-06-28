package screen

object ScreenStack {
    private val screenStack = mutableListOf<Screen>()

    fun push(screen: Screen) {
        screenStack.add(screen)
    }

    fun pop() {
        screenStack.removeLastOrNull()
    }

    fun peek(): Screen? {
        return screenStack.lastOrNull()
    }

}


//이때까지 추가했던 모든 화면들이 Screen 의 subclass 가 됨
//stack 이 데이터를 관리하고 접근하는 방식은 mutable list 로 구현
sealed class Screen