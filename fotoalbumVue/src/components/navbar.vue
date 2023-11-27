<template>
  <nav class="navbar navbar-expand-lg navbar-white bg-white">
    <div class="container">
      <img style="width: 150px" src="logo.png" alt="Descrizione immagine" />
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link">Home</router-link>
          </li>
          <button
            type="button"
            class="btn btn-warning rounded"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
          >
            Send a message
          </button>
          <!-- Modal -->
          <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">
                    Form message
                  </h1>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <form @submit.prevent="submitForm">
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="exampleFormControlInput1" class="form-label"
                        >Email address</label
                      >
                      <input
                        class="form-control"
                        placeholder="name@example.com"
                        type="text"
                        id="email"
                        v-model="objMessage.email"
                        @blur="validateEmail"
                      />
                      <p v-if="emailError" style="color: red">
                        {{ emailError }}
                      </p>
                    </div>
                    <div class="mb-3">
                      <label
                        for="exampleFormControlTextarea1"
                        class="form-label"
                        >Text</label
                      >
                      <textarea
                        id="message"
                        v-model="objMessage.textMessage"
                        @blur="validateMessage"
                        class="form-control"
                        rows="3"
                      ></textarea>
                      <p v-if="messageError" class="error">
                        {{ messageError }}
                      </p>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-bs-dismiss="modal"
                    >
                      Close
                    </button>
                    <button
                      v-if="!messageSent"
                      type="submit"
                      class="btn btn-primary"
                    >
                      Send message
                    </button>
                  </div>
                  <div
                    v-if="messageSent"
                    class="alert alert-success mt-3"
                    role="alert"
                  >
                    Message sent successfully!
                  </div>
                </form>
              </div>
            </div>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      urlApiMessages: "http://localhost:8080/api/v1/messages",
      emailError: "",
      messageError: "",
      objMessage: {
        email: "",
        textMessage: "",
      },
      messageSent: false,
    };
  },
  methods: {
    validateEmail() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (!this.objMessage.email) {
        this.emailError = "Please enter an email address.";
      } else if (!emailRegex.test(this.objMessage.email)) {
        this.emailError = "Please enter a valid email address.";
      } else {
        this.emailError = "";
      }
    },
    validateMessage() {
      if (!this.objMessage.textMessage.trim()) {
        this.messageError = "Message cannot be empty";
      } else {
        this.messageError = "";
      }
    },
    submitForm() {
      axios
        .post(this.urlApiMessages, this.objMessage)
        .then((response) => {
          console.log(response);
          this.messageSent = true;

          setTimeout(() => {
            location.reload();
          }, 2000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
.error {
  color: red;
  margin-top: 5px;
}
</style>
