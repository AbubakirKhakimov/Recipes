package uz.abubakir_khakimov.recipes.presentation.extensions

private const val NON_THIN = "[^iIl1\\.,']"

private fun textWidth(text: String): Int =
    (text.length - text.replace(regex = NON_THIN.toRegex(), replacement = "").length / 2)

fun String.ellipsizeOf(maxChar: Int): String = ellipsize(text = this, maxChar = maxChar)

fun ellipsize(text: String, maxChar: Int): String {
    if (textWidth(text = text) <= maxChar) return text

    // Start by chopping off at the word before max
    // This is an over-approximation due to thin-characters...
    var end = text.lastIndexOf(char = ' ', startIndex = maxChar - 3)

    // Just one long word. Chop it off.
    if (end == -1) return text.substring(startIndex = 0, endIndex = maxChar - 3) + "..."

    // Step forward as long as textWidth allows.
    var newEnd = end
    do {
        end = newEnd
        newEnd = text.indexOf(char = ' ', startIndex = end + 1)

        // No more spaces.
        if (newEnd == -1) newEnd = text.length
    } while (textWidth(text = text.substring(startIndex = 0, endIndex = newEnd) + "...") < maxChar)

    return text.substring(startIndex = 0, endIndex = end) + "..."
}