<template>
  <b-container>
    <div v-if="news.length">
      <b-row>
        <div>
          <div v-bind:key="data.index" v-for="data in news" class="news-post">

            <h2 class="news-post-title" v-bind:href="data.link">
              {{ data.title }}
            </h2>
            <p>
              {{ data.description }}
            </p>
            <div class="news-post-meta">
              <time>
                {{ data.date }}
              </time>
            </div>
          </div>
        </div>
      </b-row>
    </div>
    <div v-else>
      <h5>No news available yet !</h5>
    </div>
  </b-container>
</template>

<!--
<b-col l="4">
            <b-card
              v-bind:title="data.strCategory"
              v-bind:img-src="data.strCategoryThumb"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <b-card-text>{{
                `${data.strCategoryDescription.slice(0, 100)}...`
              }}</b-card-text>
              <b-button href="#" variant="primary">View food</b-button>
            </b-card>
          </b-col>
-->

<script>
  import axios from 'axios';

  export default {
    name: 'News',
    data() {
      return {
        news: []
      };
    },
    mounted() {
      axios
        .get('/api/news')
        .then(response => {
          this.news = response.data;
          console.log(this.news[0]);
        })
        .catch(err => {
          console.log(err);
        });
    }
  };
</script>
