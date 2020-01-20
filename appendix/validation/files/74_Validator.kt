package io.ipoli.android.common

class Validator<in Value, out Error>(private val rules: Map<String, PropertyValidator<Value, Error>>) {

    class DSL<Value>(private val value: Value) {

        fun <Error> check(init: Validator.Builder<Value, Error>.() -> Unit): List<Error> {
            return Validator(init).validate(value)
        }
    }

    companion object {
        private operator fun <Value, Error> invoke(init: Builder<Value, Error>.() -> Unit): Validator<Value, Error> {
            return Builder<Value, Error>().apply(init).build()
        }

        fun <Value> validate(value: Value): DSL<Value> {
            return DSL(value)
        }
    }

    fun validate(value: Value) =
        rules.map { entry ->
            entry.value.validations
                .filter { it.first.invoke(value) }
                .map { it.second }
        }.flatten()

    class Builder<Value, Error> {
        private val propertyValidators: MutableMap<String, PropertyValidator<Value, Error>> =
            mutableMapOf()

        operator fun String.invoke(init: PropertyValidator<Value, Error>.() -> Unit) {
            propertyValidators[this] = PropertyValidator<Value, Error>().apply(init)
        }

        fun build(): Validator<Value, Error> {
            return Validator(propertyValidators)
        }
    }

    class PropertyValidator<Value, Error> {
        internal var validations: MutableList<Pair<Value.() -> Boolean, Error>> = mutableListOf()

        fun given(validate: Value.() -> Boolean) = validate

        infix fun (Value.() -> Boolean).addError(error: Error) {
            validations.add(this to error)
        }
    }
}