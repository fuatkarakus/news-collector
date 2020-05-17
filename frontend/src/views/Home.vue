<template>
  <div class="home">
    <Navbar />
    <News />
  </div>
</template>

<script>
// @ is an alias to /src
import News from '@/components/News.vue';
import Navbar from '@/components/Navbar.vue';
export default {
  name: 'Home',
  components: {
    Navbar,
    News
  }
};

</script>
