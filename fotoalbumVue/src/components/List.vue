<template>
  <navbar />
  <div class="container text-center">
    <div class="d-flex justify-content-center align-items-center gap-2">
      <form @submit.prevent="searchPhoto(searchWord)" class="d-flex gap-2">
        <input
          class="rounded bg-light text-dark"
          type="text"
          id="search"
          v-model="searchWord"
          placeholder="Insert a photo title"
        />
        <button class="btn btn-secondary rounded" type="submit">Search</button>
      </form>
    </div>
  </div>
  <div class="container-fluid mt-3">
    <div class="px-lg-5">
      <!-- Photo -->
      <div class="row mt-2">
        <div
          class="col-xl-3 col-lg-4 col-md-6 mb-4"
          v-for="(photo, idx) in visiblePhotos"
          :key="idx"
        >
          <div class="bg-white rounded shadow-sm">
            <div style="height: 200px; overflow: hidden">
              <!-- Converted img because coming from Db in bytes -->
              <img
                style="width: 100%; height: 100%; object-fit: cover"
                :src="
                  photo.picture
                    ? 'data:image/png;base64,' + photo.picture
                    : 'https://wallpapers.com/images/featured/one-piece-pictures-bjm9tdff9yzguoup.jpg'
                "
                :alt="photo.title"
                class="img-fluid card-img-top"
              />
            </div>
            <div class="p-4">
              <h5>{{ photo.title }}</h5>
              <p class="small text-muted mb-2">
                {{ photo.description }}
              </p>
              <span
                v-for="(cat, idx) in photo.categories"
                :key="idx"
                :style="{ backgroundColor: cat.badgeColor, color: 'white' }"
                class="me-1 badge"
              >
                {{ cat.name }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import navbar from "./navbar.vue";
export default {
  components: {
    navbar,
  },
  data() {
    return {
      photos: [],
      urlApi: "http://localhost:8080/api/v1/photos",
    };
  },
  methods: {
    getList() {
      axios
        .get(this.urlApi)
        .then((response) => {
          this.photos = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchPhoto(search) {
      axios
        .get("http://localhost:8080/api/v1/photos?search=" + search)
        .then((response) => {
          this.photos = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    visiblePhotos() {
      return this.photos.filter((photo) => photo.visible);
    },
  },
  mounted() {
    this.getList();
  },
};
</script>