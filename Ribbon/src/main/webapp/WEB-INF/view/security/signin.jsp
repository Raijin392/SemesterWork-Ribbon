<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="Sign in">

  <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem;">
            <div class="card-body p-5 text-center">

              <div class="mb-md-5 mt-md-4 pb-5">

                <form method="POST">

                  <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                  <p class="text-white-50 mb-5">Please enter your email and password!</p>

                  <div class="form-outline form-white mb-4">
                    <input name="email" placeholder="Email" type="email" class="form-control form-control-lg" />
                  </div>

                  <div class="form-outline form-white mb-4">
                    <input name="password" placeholder="Password" type="password" class="form-control form-control-lg" />
                  </div>

                  <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

                </form>

              </div>

              <p class="mb-0" style="color: #a52834">${requestScope.errorUncorrectEmailOrPassword}</p>

              <div>
                <p class="mb-0">Don't have an account? <a href="<c:url value="/signup"/>" class="text-white fw-bold">Sign Up</a>
                </p>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

</t:mainLayout>
