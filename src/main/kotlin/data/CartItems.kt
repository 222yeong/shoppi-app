package data

object CartItems {
    //프로젝트 전역에서 상품데이터를 저장하는 클래스이므로 항상 같은 값이 유지되어야함
    private val mutableProducts = mutableMapOf<Product, Int>()
    val products: Map<Product, Int> = mutableProducts
    /*
    뮤터블 타입은 프라이빗 가시성 접근자를 사용해서 외부에서는 원소를 수정할 수 없도록 제한
    외부에 공개하는 products는 immutable 타입으로 선언해서 read only만 가능하도록 제한
     */
    fun addProduct(product: Product) {
        mutableProducts[product]?.let{
            mutableProducts[product] = it + 1  //기존에 상품이 있다면 +1만 해줌
        } ?: kotlin.run {
            mutableProducts[product] = 1  //기존에 상품이 없다면 1을 할당
        }
        //뮤터블프로덕트에 수량정보 추가. 기존에 상품이 있는지 확인
        //맵에서 키를 줬을 때 없을수도 있으니까 nullable 타입.
        //따라서 항상 safe call operator와 함께 써야함
    }
}