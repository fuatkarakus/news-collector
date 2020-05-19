<template>
  <b-container>
    <div v-if="news.length">
      <b-row>
        <div>
          <div v-bind:key="data.index" v-for="data in news" class="blog-post">
            <h2 class="blog-post-title" >
              <a v-bind:href="data.link" target="_blank">
                {{ data.title }}
              </a>
            </h2>
            <p>
              {{ data.content }}
            </p>
            <div class="blog-post-meta">
              <time v-if="data.date !== null">
                {{ data.date }}
              </time>
              <time v-else>
                {{ data.createdDate }}
              </time>
              <div class="post-tags">
                <a> {{ data.publisher }}</a>
              </div>
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
      .get('/api/news/latest')
      .then(response => {
        this.news = response.data;
        //console.log(this.news[0]);
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
