// Import the functions you need from the SDKs you need
      import { initializeApp } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-app.js";
      import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-analytics.js";
      import {
        getStorage,
        ref,
        uploadBytes,
        getDownloadURL,
      } from "https://www.gstatic.com/firebasejs/10.9.0/firebase-storage.js";

      // TODO: Add SDKs for Firebase products that you want to use
      // https://firebase.google.com/docs/web/setup#available-libraries

      // Your web app's Firebase configuration
      // For Firebase JS SDK v7.20.0 and later, measurementId is optional
      const firebaseConfig = {
        apiKey: "AIzaSyBgj0UQl3hLIoppfnSzHXv8L5MKTeaYfGA",
        authDomain: "fir-web-dffc1.firebaseapp.com",
        projectId: "fir-web-dffc1",
        storageBucket: "fir-web-dffc1.appspot.com",
        messagingSenderId: "895111528200",
        appId: "1:895111528200:web:8954aa09a5fd133f1b76bd",
        measurementId: "G-LTRTDGDF8G",
      };

      // Initialize Firebase
      const app = initializeApp(firebaseConfig);
      const storage = getStorage(app);

      // vue
      // import { createApp } from "https://unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js";
      import { createApp } from "./vue.esm-browser.prod.js";
      const xxx = createApp({
        data: function () {
          return {
            isShow: true,
            findId: 123,
            products: ["a", "b", "c"],
            photo: "",
            trails: null,
            trail: {},
            isShowInsert: false,
            modal: null,
            exampleref: null,
            exampleobj: null,
          };
        },
        methods: {
          uploadImage() {
            const input = document.getElementById("yourFileInputId"); // Ensure you have the correct ID
            const imageFile = input.files[0];
            if (!imageFile) {
              console.log("No file selected.");
              return;
            }
            const storageRef = ref(storage, "images/" + imageFile.name);
            return uploadBytes(storageRef, imageFile)
              .then((snapshot) => {
                console.log("Uploaded a blob or file!", snapshot);
                // Return or process the URL as needed
                return getDownloadURL(snapshot.ref);
              })
              .catch((error) => {
                console.error("Upload failed", error);
                throw error; // Propagate error
              });
          },

          callCreate: async function () {
            try {
              console.log("create!");
              console.log(this.trail);

              // 先將照片上傳至firebase，並取得回傳url
              const url = await this.uploadImage();
              console.log("Image uploaded, URL: ", url);
              this.trail.photo = url;
              console.log(this.trail.photo);

              // Now that the photo URL is set, we can proceed with the backend request
              let request = this.trail;
              console.log("--------");
              // console.log(request);
              

              const response = await axios.post(
                "http://localhost:25565/dto/addDto", request
              );
              // Use the response as needed

              // Update screen, assuming this.exampleModal.hide() and this.callFind()
              // don't return Promises. If they do, you might want to await them as well.
              this.exampleModal.hide();
              this.callFind();
            } catch (error) {
              console.error("An error occurred:", error);
            }
          },

          callRemove: function (idRemoved) {
            console.log("callRemove!", idRemoved);

            axios
              .delete("http://localhost:25565/dto/deleteDto/" + idRemoved)
              .then((response) => {
                // console.log(response.data);a

                this.callFind();
              })
              .catch(function (error) {
                console.log(error);
              });
          },

          callModify: function () {
            // console.log(this.trail.name)
            console.log(this.trail);
            if (this.trail.id == "") {
              trail.id = null;
            }

            let request = this.trail;
            axios
              .put(
                "http://localhost:25565/dto/updateDto/" + this.trail.id,
                request
              )
              .then((response) => {
                console.log(response.data);
                this.exampleModal.hide();
                this.callFind();
              })
              .catch(function (error) {
                console.log(error);
              });
            // .then(function (response) {
            //     console.log(response.data);
            // })
            // this.exampleModal.hide();
            // this.callFind();
          },

          showModal: function () {
            exampleobj.value.show();
          },
          hideModal: function () {
            exampleobj.value.hide();
          },

          doclick1: function (action, id) {
            console.log("doclick1", action, id);

            if (action === "insert") {
              this.isShowInsert = true;
              // product.value = {};
              this.trail = {};
              console.log(this.isShowInsert);
            } else {
              console.log("else");
              this.isShowInsert = false;
              this.callFindById(id);
            }
            this.exampleModal.show(); // Correctly calling show on the modal instance
          },
          callFindById: function (id) {
            console.log("test", id);
            axios
              .get("http://localhost:25565/dto/findTrailById.controller/" + id)
              .then((response) => {
                // Use arrow function here
                console.log("findById", response.data);
                this.trail = response.data; // Now 'this' correctly refers to the Vue instance
              })
              .catch(function (error) {
                console.log(error);
              });
          },

          callFind: function () {
            console.log("test callFind!");
            let url = "http://localhost:25565/trailDto.controller";
            axios
              .get(url)
              .then((response) => {
                console.log("response", response.data);
                this.trails = response.data;
                // this.photo = response.data[20].photo;
              })
              .catch(function (error) {
                console.log("error", error);
              });
          },
        },
        mounted() {
          this.callFind(); // 在這裡調用callFind

          //   this.$refs.input.focus(); // Correctly accessing ref

          // Correctly initializing Bootstrap modal using Vue ref
          const modalElement = this.$refs.exampleref;
          if (modalElement) {
            this.exampleModal = new bootstrap.Modal(modalElement);
          }
        },
      });
      xxx.mount("#app");
    