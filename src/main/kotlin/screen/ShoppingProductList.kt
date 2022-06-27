package screen
//카테고리별 상품목록 관리, 사용자가 요청한 상품의 목록을 출력
import data.Product  //data 패키지의 product 클래스를 이용해서 배열에 추가

class ShoppingProductList {
    private val products = arrayOf(
        Product("패션", "겨울 패딩"),
        Product("패션", "겨울 바지"),
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "건식사료"),
        Product("반려동물용품", "습식사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식")
    )

    private val categories: Map<String, List<Product>> = products.groupBy {product ->
        product.categoryLabel
    }
    //사용자가 선택한 카테고리의 상품 목록을 표시.
    //배열에서 제공하는 groupBy 연산자를 이용해 categoryLabel을 기준으로 해당하는 상품을 쉽게 조회
    //할 수 있는 categories 변수를 추가함함

    fun showProducts(selectedCategory: String) {   //selectedCategory:받은 상품명
        val categoryProducts = categories[selectedCategory]
        if(!categoryProducts.isNullOrEmpty()) {   //상품목록이 하나라도 존재하면
            println("""
                ***=========================***
                선택하신 [$selectedCategory] 카테고리 상품입니다.
                """.trimIndent())
            val productSize = categoryProducts.size
            for (index in 0 until productSize) {   //그냥 until만 쓰는거
                println("${index}. ${categoryProducts[index].name}")
            }
        } else {
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String) {
        println("[$selectedCategory] 카테고리의 상품이 등록되기 전입니다.")
    }
}