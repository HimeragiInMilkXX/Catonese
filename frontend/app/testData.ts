import type { Comment } from "./types/types";

export const comments: Comment[] = [
    {
        id: 1,
        title: 'Its very good!',
        comment: 'I have to work everyday from 9am to 7pm but still, with Catonese, I managed to learn Cantonese within 5 months and I can already take with natives however I want! Also the meetups are great!',
        rating: 5,
        user: { username: 'oscarhimself', userId: 1, email: 'oscarIsgay@gmail.com', nationality: 'JP' }
    },
    {
        id: 2,
        title: 'Best app for tones',
        comment: 'The speech recognition is actually accurate for Cantonese tones. It helped me stop sounding like a tourist within weeks.',
        rating: 4.8,
        user: { username: 'sarah_uk', userId: 2, email: 'sarah.j@outlook.com', nationality: 'GB' }
    },
    {
        id: 3,
        title: 'Great community',
        comment: 'I love the community forum. Whenever I have a question about slang, someone answers within minutes. Highly recommend!',
        rating: 4.5,
        user: { username: 'mike_travels', userId: 3, email: 'mike1992@gmail.com', nationality: 'US' }
    },
    {
        id: 4,
        title: 'Slow progress but worth it',
        comment: 'Cantonese is hard, but the lessons are broken down into manageable chunks. I wish there were more advanced grammar sections though.',
        rating: 3.9,
        user: { username: 'hannah_kim', userId: 4, email: 'h.kim88@daum.net', nationality: 'KR' }
    },
    {
        id: 5,
        title: 'The meetups are great',
        comment: 'I attended the virtual meetup last Saturday. It was intimidating at first, but everyone was so supportive. My fluency is peaking!',
        rating: 5.0,
        user: { username: 'pierre_linguo', userId: 5, email: 'pierre.dubois@free.fr', nationality: 'FR' }
    },
    {
        id: 6,
        title: 'For busy professionals',
        comment: 'I use this during my commute on the MTR. The bite-sized lessons are perfect for 15-minute windows.',
        rating: 4.2,
        user: { username: 'chen_expert', userId: 6, email: 'w.chen@techcorp.com', nationality: 'SG' }
    },
    {
        id: 7,
        title: 'A bit pricey but good',
        comment: 'The subscription is a little higher than other apps, but the quality of the native recordings makes it worth the investment.',
        rating: 4.0,
        user: { username: 'dr_miller', userId: 7, email: 'j.miller@university.edu', nationality: 'AU' }
    },
    {
        id: 8,
        title: 'Incredible Audio Quality',
        comment: 'Finally an app that doesn’t use robotic voices! Hearing real people speak makes a huge difference for my listening comprehension.',
        rating: 4.9,
        user: { username: 'elena_polyglot', userId: 8, email: 'elena.rossi@gmail.it', nationality: 'IT' }
    },
    {
        id: 9,
        title: 'Helped me a lot',
        comment: 'My wife’s family speaks Cantonese and I can finally join the dinner conversations. They were so impressed!',
        rating: 5.0,
        user: { username: 'lucas_v', userId: 9, email: 'lucas.v@web.de', nationality: 'DE' }
    },
    {
        id: 10,
        title: 'Needs more vocabulary',
        comment: 'The interface is beautiful and the flow is good, but I finished the current content faster than expected. Hope they add more levels soon.',
        rating: 3.5,
        user: { username: 'anita_b', userId: 10, email: 'anita.bakri@yahoo.com', nationality: 'MY' }
    }
];

export const types = [ "Help", "Feedback", "Report", "Advertising", "Recruitment", "Request", "Collaboration" ]

import type { ChartData, ChartOptions } from 'chart.js'

export const cantoneseLineData: ChartData<'line'> = {
    labels: ['1990', '1995', '2000', '2005', '2010', '2015', '2020', '2025'],
    datasets: [
        {
            label: 'Estimated Cantonese Speakers (millions)',
            data: [60, 63, 66, 69, 72, 75, 78, 80], // fictional but plausible
            borderColor: '#01bafe',
            backgroundColor: '#01bafe',
            tension: 0,          // straight lines, no curve
            pointRadius: 3,
            pointHoverRadius: 5,
        },
    ],
}

export const cantoneseLineOptions: ChartOptions<'line'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
        legend: {
        display: true,
        labels: {
            boxWidth: 12,
        },
        },
        title: {
        display: true,
        text: 'Estimated Worldwide Cantonese-Speaking Population (1990–2025)',
        align: 'center',
        color: "#002533",
        padding: 24,
        font: {
            size: 16,
            weight: 'bold',
        },
        },
        tooltip: {
        callbacks: {
            label: ctx => {
            const value = ctx.parsed.y
            return `${value} million`
            },
        },
        },
    },
    scales: {
        y: {
        title: {
            display: true,
            text: 'Millions of speakers',
        },
        beginAtZero: false,
        suggestedMin: 55,
        suggestedMax: 85,
        },
        x: {
        title: {
            display: true,
            text: 'Year',
        },
        },
    },
}

export const learningTimePieData: ChartData<'doughnut'> = {
    labels: ['~50 hours', '~100 hours', '~200 hours'],
    datasets: [
        {
        label: 'Hours until simple Cantonese conversation',
        data: [20, 55, 25], // number of learners
        backgroundColor: ['#67d6fe', '#ffe699', '#e6f8ff'],
        hoverOffset: 6,
        },
    ],
}

export const learningTimePieOptions: ChartOptions<'doughnut'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
        legend: {
        position: 'bottom',
        labels: { padding: 24 }
        },
        title: {
        display: true,
        padding: 24,
        align: "center",
        color: "#002533",
        text: 'Study Hours to Reach Simple Conversation',
        font: { size: 16 }
        },
        tooltip: {
        callbacks: {
            label: ctx => {
            const label = ctx.label || ''
            const value = ctx.parsed
            return `${label}: ${value} learners`
            },
        },
        },
    },
}