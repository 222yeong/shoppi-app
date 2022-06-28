package screen

import data.CartItems

class ShoppingCart : Screen() {
    private  val products = CartItems.products       //CartItems.kt의 val products 사용
                                                     //products = <Product, Int> 3개
                                                     //Product = (categoryLabel, name)
    fun showCartItems(){
         ScreenStack.push(this)
        if (products.isNotEmpty()) {
            println(
                products.keys.joinToString( //해당상품 정보까지 출력
                    separator = ", \n",
                    prefix = """
                        ***============================***
                        장바구니에 담은 상품 목록 입니다.
                        
                    """.trimIndent()
                ) { product ->
                    "카테고리: ${product.categoryLabel} / 상품명: ${product.name} / 수량: ${products[product]}"
                }
            )
        } else {
            println("""
                장바구니에 담긴 상품이 없습니다.
            """.trimIndent())
        }

    }
}