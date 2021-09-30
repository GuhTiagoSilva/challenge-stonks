import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService,
    private route: Router,
    private toast: ToastrService
  
    ) { }

  loginForm: FormGroup;
  invalidCredentials: boolean = false;

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  handleLogin() {
    if(this.loginForm.valid) {
      this.service.authenticate(this.loginForm.get('email').value, this.loginForm.get('password').value).subscribe(response => {
        this.toast.success('Login realizado com sucesso!');
        this.route.navigate(['home']);
      }, error => {
        this.invalidCredentials = true;
        this.toast.error('Credenciais Inválidas');
      })
    } else {
      this.toast.error('Preencha o formulário para realizar o login');
    }
  }

}
