package ru.miel.domain

import ru.miel.R
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.Quotes
import ru.miel.utils.convertStringToLocalDate

/**
 * APP CONFIG
 */

/**
 * COMMON
 */
const val EMPTY_STRING = ""

/**
 * REMOTE SETTINGS
 */
const val BASE_URL = "http://80.85.246.168"

/**
 * BUNDLE KEYS
 */
//const val TITLE = "title"
//const val ID = "id"

/**
 * DATE PATTERNS
 */
const val DATE_PATTERN_DEFAULT = "dd.MM.yyyy"
const val DATE_PATTERN_WITH_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_WITHOUT_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_LESSON_IGOTIT = "yyyy-MM-dd - HH:mm"
const val DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH = "dd MMMM yyyy"
const val DATE_PATTERN_WEEK_DAY = "EE"
const val DATE_PATTERN_DAY_ONLY = "dd"
const val DATE_PATTERN_WITHOUT_YEAR = "dd MMMM, EEEE"

/**
 * SAMPLE DATE FOR DATABASE
 */

val sampleListOfQuotes = listOf(
    Quotes(
        startDate = "16.12.2024".convertStringToLocalDate(DATE_PATTERN_DEFAULT),
        endDate = "22.12.2024".convertStringToLocalDate(DATE_PATTERN_DEFAULT),
        quotes = 3,
        quotesRemaining = 3,
    ),
    Quotes(
        startDate = "23.12.2024".convertStringToLocalDate(DATE_PATTERN_DEFAULT),
        endDate = "29.12.2024".convertStringToLocalDate(DATE_PATTERN_DEFAULT),
        quotes = 3,
        quotesRemaining = 3,
    ),
)

val sampleListOfCandidates = listOf(
    Candidates(
        img = R.drawable.img_avatar,
        name = "Романова1 Мария Ивановна",
        year = "22 года",
        city = "Москва",
        isFavorite = false,
        realtor = "Введение в профессию риелтор (пройден)",
        juridicalCourse = "Базовый юридический курс (в процессе)",
        mortgage = "Курс “Ипотека” (в процессе)",
        taxation = "Курс “Налогообложение” (не начат)",
        objects = "Объекты 5",
        clients = "Клиенты 5",
        isInvite = false
    ),
    Candidates(
        img = R.drawable.img_avatar,
        name = "Романова2 Мария Ивановна",
        year = "22 года",
        city = "Москва",
        isFavorite = false,
        realtor = "Введение в профессию риелтор (пройден)",
        juridicalCourse = "Базовый юридический курс (в процессе)",
        mortgage = "Курс “Ипотека” (в процессе)",
        taxation = "Курс “Налогообложение” (не начат)",
        objects = "Объекты 5",
        clients = "Клиенты 5",
        isInvite = false
    ),
    Candidates(
        img = R.drawable.img_avatar,
        name = "Романова3 Мария Ивановна",
        year = "22 года",
        city = "Москва",
        isFavorite = false,
        realtor = "Введение в профессию риелтор (пройден)",
        juridicalCourse = "Базовый юридический курс (в процессе)",
        mortgage = "Курс “Ипотека” (в процессе)",
        taxation = "Курс “Налогообложение” (не начат)",
        objects = "Объекты 5",
        clients = "Клиенты 5",
        isInvite = false
    ),
    Candidates(
        img = R.drawable.img_avatar,
        name = "Романова4 Мария Ивановна",
        year = "22 года",
        city = "Москва",
        isFavorite = false,
        realtor = "Введение в профессию риелтор (пройден)",
        juridicalCourse = "Базовый юридический курс (в процессе)",
        mortgage = "Курс “Ипотека” (в процессе)",
        taxation = "Курс “Налогообложение” (не начат)",
        objects = "Объекты 5",
        clients = "Клиенты 5",
        isInvite = false
    ),
    Candidates(
        img = R.drawable.img_avatar,
        name = "Романова5 Мария Ивановна",
        year = "22 года",
        city = "Москва",
        isFavorite = false,
        realtor = "Введение в профессию риелтор (пройден)",
        juridicalCourse = "Базовый юридический курс (в процессе)",
        mortgage = "Курс “Ипотека” (в процессе)",
        taxation = "Курс “Налогообложение” (не начат)",
        objects = "Объекты 5",
        clients = "Клиенты 5",
        isInvite = false
    ),
)