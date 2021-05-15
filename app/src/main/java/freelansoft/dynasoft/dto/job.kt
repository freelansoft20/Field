package freelansoft.dynasoft.dto

data class job(var genus:String, var specimen:String, var common:String) {
    override fun toString(): String {
        return common
    }
}