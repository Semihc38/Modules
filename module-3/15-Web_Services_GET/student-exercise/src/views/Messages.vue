<template>
  <div class="messages">
    <topic-details v-bind:topic-id="$route.params.id" />
  </div>
</template>

<script>
import TopicDetails from '@/components/TopicDetails.vue';
import TopicList from '@/components/TopicList.vue'

export default {
  name: 'Messages',
  components: {
    TopicDetails,
    TopicList
  }
}
</script>
