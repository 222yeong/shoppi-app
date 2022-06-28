package extensions
//top-level의 함수로 확장함수를 구현할것
//nullable String의 기능을 확장

fun String?.getNotEmptyString(): String {
    var input = this
    while(input.isNullOrBlank()) {
        println("값을 입력해주세요")
        input = readLine()
    }
    return input.trim()    //input에 공백을 제거해서 반환
}

fun String?.getNotEmptyInt(): Int {
    var input = this?.trim()
    while(input.isNullOrEmpty() || input.toIntOrNull() == null) {
        println("값을 입력해주세요")
        input = readLine()
    }
    return input.toInt()
}
//사용자에게 상품번호를 입력받을 때 그 값이 int 타입으로 변환할 수 있는 값인지 판별