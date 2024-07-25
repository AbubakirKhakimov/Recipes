package uz.abubakir_khakimov.recipes.presentation.extensions

typealias FieldId = String
typealias FieldValue = String

fun getAutofillFieldsJs(vararg fields: Pair<FieldId, FieldValue>): String {
    var js = "javascript:( function() { "

    fields.forEach { (fieldId, fieldValue) ->
        js += "document.getElementById('$fieldId').value = '$fieldValue'; "
    }

    js += "} )()"

    return js.also { js += " })()" }
}

fun getButtonClickJs(className: String): String = "javascript:( function() { " +
        "document.getElementsByClassName('$className')[0].click(); } )()"