package screen
//카테고리별 상품목록 관리, 사용자가 요청한 상품의 목록을 출력
import LINE_DiViDER
import data.CartItems
import data.Product  //data 패키지의 product 클래스를 이용해서 배열에 추가
import extensions.getNotEmptyInt
import extensions.getNotEmptyString

class ShoppingProductList {
    private val products = arrayOf(                             //private: 선언한 클래스 내부에서만 참조 가능
        Product("패션", "겨울 패딩"),
        Product("패션", "겨울 바지"),             //product.kt의 data class
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "건식사료"),
        Product("반려동물용품", "습식사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식")
    )

    //list 의 product 와 문자열을  mapping.  list 와 map 둘 다 immutable

    private val categories: Map<String, List<Product>> = products.groupBy {product ->
        product.categoryLabel
    }
    //categoryLabel 을 키값으로 갖는 레이블
    //category 에 해당하는 상품목록이 value

    /*사용자가 선택한 카테고리의 상품 목록을 표시.
    배열에서 제공하는 groupBy 연산자를 이용해 categoryLabel 을 기준으로 해당하는 상품을 쉽게 조회
    할 수 있는 categories 변수를 추가함*/


    fun showProducts(selectedCategory: String) {      //selectedCategory:받은 상품명
        val categoryProducts = categories[selectedCategory]
        if(!categoryProducts.isNullOrEmpty()) {       //상품목록이 하나라도 존재하면
            println("""
                $LINE_DiViDER
                선택하신 [$selectedCategory] 카테고리 상품입니다.
                """.trimIndent())

            /*
            val productSize = categoryProducts.size
            for (index in 0 until productSize) {   //그냥 until 만 쓰는거
                println("${index}. ${categoryProducts[index].name}")
            }
            */
            categoryProducts.forEachIndexed { index, product ->
                println("${index}. ${product.name}")
            }
            showCartOption(categoryProducts, selectedCategory)
        } else {
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showCartOption(categoryProducts: List<Product>, selectedCategory: String) {
        println(
            """
                $LINE_DiViDER
                장바구니에 담을 상품 번호를 선택해주세요.
            """.trimIndent()
        )

        val selectedIndex = readLine().getNotEmptyInt()
        categoryProducts.getOrNull(selectedIndex)?.let { product ->
            CartItems.addProduct(product)                             //CartItems.kt 의 addProduct 함수
            println("=> 장바구니로 이동하시려면 #을, 계속 쇼핑하시려면 *을 입력해주세요.")
            val answer = readLine().getNotEmptyString()
            if (answer == "#") {
                val shoppingCart = ShoppingCart()
                shoppingCart.showCartItems()
            } else if (answer == "*") {
                showProducts(selectedCategory)
            } else {
                //TODO
            }
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String) {
        println("[$selectedCategory] 카테고리의 상품이 등록되기 전입니다.")
    }
}