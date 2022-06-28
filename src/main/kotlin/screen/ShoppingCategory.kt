package screen

import extensions.getNotEmptyString

class ShoppingCategory : Screen(){

    fun showCategories() {
        ScreenStack.push(this)
        val categories = arrayOf("패션", "전자기기", "반려동물용품")

        for (category in categories) {                           //카테고리 3개 보여줌
            println(category)
        }
        println("=> 장바구니로 이동하시려면 #을 입력해주세요")

        val selectedCategory = readLine().getNotEmptyString()
        /*
        while (selectedCategory.isNullOrBlank()) {               //카테고리 입력할 때까지 받음
            println("값을 입력해주세요")
            selectedCategory = readLine()
        }
        */

        if (selectedCategory == "#") {                            //#을 누르면 장바구니로 이동하여 목록보여줌
            val shoppingCart = ShoppingCart()
            shoppingCart.showCartItems()
        } else {
            if (categories.contains(selectedCategory)) {
                val shoppingProductList = ShoppingProductList(selectedCategory)
                shoppingProductList.showProducts()  //입력한 카테고리의 목록 보여줌
            } else {
                showErrorMessage(selectedCategory)                  //그 외에는 존재하지 않는 카테고리
            }

        }
    }

    private fun showErrorMessage(selectedCategory: String?) {
        println("[$selectedCategory] : 존재하지 않는 카테고리 입니다. 다시 입력해주세요.")
        showCategories()
    }
}