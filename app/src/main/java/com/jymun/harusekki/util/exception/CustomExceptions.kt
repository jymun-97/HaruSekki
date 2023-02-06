package com.jymun.harusekki.util.exception

sealed class CustomExceptions(
    override val message: String
) : Throwable(message) {

    class FailToConnectServerException : CustomExceptions("서버와의 연결에 실패하였습니다. 다시 시도해 주세요.")

    class InvalidNetworkException : CustomExceptions("네트워크 연결이 원활하지 않습니다.")

    class InvalidAccessException : CustomExceptions("잘못된 접근입니다. 다시 시도해 주세요.")

    class NotDataExistException : CustomExceptions("검색 조건에 일치하는 결과가 없습니다.")

    class EmptySearchKeywordException : CustomExceptions("검색할 키워드를 입력하세요.")

    class NothingSelectedException : CustomExceptions("검색할 항목을 선택하세요.")
}