    package com.example.codingexamui.presentation.login

    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.codingexamui.domain.exception.PasswordValidationException
    import com.example.codingexamui.domain.exception.UsernameValidationException
    import com.example.codingexamui.domain.use_case.AuthUseCase
    import com.example.codingexamui.domain.use_case.LoginUseCase
    import com.example.codingexamui.presentation.login.event.LoginEvent
    import com.example.codingexamui.presentation.login.event.LoginUiEvent
    import com.example.codingexamui.presentation.login.state.LoginState
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableSharedFlow
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.asSharedFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.flow.update
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class LoginViewModel @Inject constructor(
        private val authUseCase: AuthUseCase
    ): ViewModel() {


        private val _state = MutableStateFlow(LoginState())
        val state = _state.asStateFlow()

        private val _event = MutableSharedFlow<LoginUiEvent>()
        val event = _event.asSharedFlow()

        private fun login(userName: String, password: String) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }

                kotlinx.coroutines.delay(2000)

                kotlin.runCatching {
                    authUseCase.loginUseCase(userName, password)
                }.onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    _event.emit(LoginUiEvent.LoginSuccess)
                }.onFailure { exception ->
                    _state.update { it.copy(isLoading = false) }
                    handleLoginException(exception)
                }
            }
        }

        private  suspend fun handleLoginException(exception: Throwable) {
                when(exception){
                    is UsernameValidationException -> {
                        _event.emit(LoginUiEvent.UserNameError(exception.message ?: "Username error"))
                    }
                    is PasswordValidationException -> {
                        _event.emit(LoginUiEvent.PasswordError(exception.message ?: "Password error"))
                    }
                }

        }
        fun onEvent(event: LoginEvent) {
            when (event) {
                is LoginEvent.Login -> {
                    login(event.userName, event.password )
                }
            }
        }
    }