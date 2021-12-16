package web.core

sealed class Either<out L, out R> {
    companion object {
        fun <R> catching(fn: () -> R): Either<Exception, R> =
            try {
                Right(fn())
            } catch(e: Exception) {
                Left(e)
            }

        suspend fun <R> suspendCatching(fn: suspend () -> R): Either<Exception, R> =
                try {
                    Right(fn())
                } catch(e: Exception) {
                    Left(e)
                }
    }
    abstract val left: L?
    abstract val right: R?
}

data class Left<out L>(override val left: L): Either<L, Nothing>() {
    override val right: Nothing?= null
}
data class Right<out R>(override val right: R): Either<Nothing, R>() {
    override val left: Nothing? = null
}

fun <L, R> List<Either<L, R>>.liftRight(): Either<List<L>, List<R>> {
    val lefts = this.flatMap { it.left?.let(::listOf)?: emptyList() }

    if(lefts.isNotEmpty()) {
        return Left(lefts)
    } else {
        return Right(this.flatMap { it.right?.let(::listOf) ?: emptyList() })
    }
}

fun <L, R> List<Either<L, R>>.liftLeft(): Either<List<L>, List<R>> {
    val rights = this.flatMap { it.right?.let(::listOf)?: emptyList() }

    if(rights.isNotEmpty()) {
        return Right(rights)
    } else {
        return Left(this.flatMap { it.left?.let(::listOf) ?: emptyList() })
    }
}

fun <L, T, R> Either<L, R>.mapLeft(fn: (L) -> T): Either<T, R> =
    when(this) {
        is Left -> Left(fn(left))
        is Right -> Right(right)
    }

fun <L,T, R> Either<L, R>.bindLeft(fn: (L) -> Either<T, R>): Either<T, R> =
        when(this) {
            is Left -> fn(left)
            is Right -> Right(right)
        }

suspend fun <L,T, R> Either<L, R>.suspendBindLeft(fn: suspend (L) -> Either<T, R>): Either<T, R> =
        when(this) {
            is Left -> fn(left)
            is Right -> Right(right)
        }

fun <L, R, S> Either<L, R>.mapRight(fn: (R) -> S): Either<L, S> =
        when(this) {
            is Left -> Left(left)
            is Right -> Right(fn(right))
        }

fun <L, R, R2> Either<L, R>.bindRight(fn: (R) -> Either<L, R2>): Either<L, R2> =
        when(this) {
            is Left -> Left(left)
            is Right -> fn(right)
        }

suspend fun <L, R, R2> Either<L, R>.suspendBindRight(fn: suspend (R) -> Either<L, R2>): Either<L, R2> =
        when(this) {
            is Left -> Left(left)
            is Right -> fn(right)
        }